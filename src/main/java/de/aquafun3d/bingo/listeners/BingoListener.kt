package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.helpers.Mode
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.tasks.*
import de.aquafun3d.bingo.utils.teams.ITeams
import de.aquafun3d.bingo.utils.timer.ITimer
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.advancement.Advancement
import org.bukkit.block.Biome
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack

class BingoListener(private val _helper: IHelpers, private val _teamInv: ITeamInventories, private val _teams: ITeams, private val _settings: ISettings, private val _timer: ITimer, private val _taskManager: IBingoTaskManager) : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Settings", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Bingo", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (item == null) return
        if(!_helper.isBingoRunning()) return
        checkItem(player, item)
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        if(!_helper.isBingoRunning()) return
        if (e.entity is Player){
            val player = (e.entity as Player)
            val item = e.item.itemStack
            if(item == _teamInv.getItem()) e.isCancelled = true
            checkItem(player, item)
        }
    }

    @EventHandler
    fun onMobKill(e: EntityDeathEvent){
        if(!_helper.isBingoRunning()) return
        if(e.entity.killer is Player){
            checkMob(e.entity.killer!!, e.entity.type)
        }
    }

    @EventHandler
    fun onAdvancement(e: PlayerAdvancementDoneEvent){
        if(!_helper.isBingoRunning()) return
        checkAdvancement(e.player, e.advancement)
    }

    @EventHandler
    fun onBiome(e: PlayerMoveEvent){
        if(e.hasChangedBlock()){
            checkBiome(e.player, e.player.location.block.biome)
        }
    }


    private fun checkItem(player: Player, item: ItemStack){
        if(_teams.getPlayerTeamName(player) == "spec") return
        for(i in _teamInv.getInventorybyPlayer(player)){
            if(i == null) continue
            if(!item.hasItemMeta() && i.type == item.type){
                _teamInv.removeItem(player, item.type)
                announce(player, item)
            }
        }
    }

    private fun checkMob(player: Player, mob: EntityType){
        if(_teams.getPlayerTeamName(player) == "spec") return
        for(task in _taskManager.getList()){
            if(task.getTaskType() == TaskType.MOB){
                task as MobTask
                if(task.getEntityType() == mob){
                    _teamInv.removeItem(player, task.getItemStack())
                    announceName(player, task.getName())
                }
            }
        }
    }

    private fun checkAdvancement(player: Player, advancement: Advancement){
        if(_teams.getPlayerTeamName(player) == "spec") return
        for(task in _taskManager.getList()){
            if(task.getTaskType() == TaskType.ACHIEVMENT){
                task as AdvancementTask
                if(task.getAdvancement() == advancement){
                    _teamInv.removeItem(player, task.getItemStack())
                    announceName(player, task.getName())
                }
            }
        }
    }

    private fun checkBiome(player: Player, biome: Biome){
        if(_teams.getPlayerTeamName(player) == "spec") return
        for(task in _taskManager.getList()){
            if(task.getTaskType() == TaskType.BIOME){
                task as BiomeTask
                if(task.getBiome() == biome){
                    _teamInv.removeItem(player, task.getItemStack())
                    announceName(player, task.getName())
                }
            }
        }
    }

    private fun announce(player: Player, item: ItemStack){
        _teams.updateTeamSuffix(player, _teamInv.itemCount(player))
        val trans = item.displayName() as TranslatableComponent
        val name = trans.args()[0]
        _helper.atAll(Component.text("Team ", NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text(player.name, NamedTextColor.AQUA)).append(Component.text(" registered ", NamedTextColor.GREEN)).append(name.color(NamedTextColor.LIGHT_PURPLE)).append(_teams.getSuffix(player)))
        for(p in Bukkit.getOnlinePlayers()){
            if(_teams.getPlayerTeam(p) == _teams.getPlayerTeam(player))
                sendTitle(p, name.color(NamedTextColor.LIGHT_PURPLE), Component.text("registered", NamedTextColor.GREEN))
        }
        if(_teamInv.getInventorybyPlayer(player).isEmpty) winTask(player)
        if(_settings.getMode() == Mode.LOCKOUT && _teamInv.itemCount(player) == _settings.getQuantity() * 9 / 2 + 1) winTask(player)

    }

    private fun announceName(player: Player, name: Component){
        _helper.atAll(Component.text("Team ", NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text(player.name, NamedTextColor.AQUA)).append(Component.text(" registered ", NamedTextColor.GREEN)).append(name.color(NamedTextColor.LIGHT_PURPLE)).append(_teams.getSuffix(player)))
        for(p in Bukkit.getOnlinePlayers()){
            if(_teams.getPlayerTeam(p) == _teams.getPlayerTeam(player))
                sendTitle(p, name.color(NamedTextColor.LIGHT_PURPLE), Component.text("registered", NamedTextColor.GREEN))
        }
        if(_teamInv.getInventorybyPlayer(player).isEmpty) winTask(player)
        if(_settings.getMode() == Mode.LOCKOUT && _teamInv.itemCount(player) == _settings.getQuantity() * 9 / 2 + 1) winTask(player)
    }

    private fun sendTitle(player: Player, title: Component, subtitle: Component){
        Bukkit.getEntity(player.uniqueId)
        val list = mutableListOf<Player>()
        for(k in _teams.getTeams().keys){
            if(_teams.getTeams()[k] == _teams.getPlayerTeam(player)){
                list.add(Bukkit.getEntity(k) as Player )
            }
        }
        val audi = Audience.audience(list)
        audi.showTitle(Title.title(title,subtitle))
        audi.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f))
    }

    private fun winTask(player: Player){
        _helper.atAll(Component.text("Time played: ",NamedTextColor.GOLD).append(Component.text(_timer.getTime(), NamedTextColor.GREEN)))
        _timer.reset()
        val audi = Audience.audience(Bukkit.getOnlinePlayers())
        audi.showTitle(Title.title(Component.text("Team ",NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)), Component.text("has won the Bingo!", NamedTextColor.GRAY)))
        audi.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f))
        for(p in Bukkit.getOnlinePlayers()){
            p.gameMode = GameMode.SPECTATOR
        }
        _helper.atAll(Component.text("Team ",NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text("won the Bingo!", NamedTextColor.GOLD)))
    }
}
