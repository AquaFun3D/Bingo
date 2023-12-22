package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.helpers.Mode
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class SettingsInventory(private val _helper: IHelpers, private val _settings: ISettings) : ISettingsInventory {
    private val _inventory: Inventory = Bukkit.createInventory(null, 45, Component.text("Settings", NamedTextColor.DARK_PURPLE))
    private val _item: ItemStack = _helper.newItem(Material.NETHER_STAR, Component.text("Settings", NamedTextColor.DARK_AQUA))

    init {
        newInventory()
    }

    override fun newInventory() {
        _settings.setConfirmed(false)
        val empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(" "))
        val confirm = _helper.newItem(Material.LIME_DYE, Component.text("Confirm", NamedTextColor.GREEN))
        val bingoDifficulty = _helper.newItem(Material.TOTEM_OF_UNDYING, Component.text("Bingo Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getBingoDifficulty().toString()).color(NamedTextColor.AQUA)))
        val quantity = _helper.newItem(Material.FIRE_CHARGE, _settings.getQuantity() * 9, Component.text("Items", NamedTextColor.AQUA))
        val teamsize = _helper.newItem(Material.ARMOR_STAND, _settings.getTeamsize(), Component.text("Teamsize", NamedTextColor.DARK_AQUA))
        val difficulty = _helper.newItem(Material.NETHER_STAR, Component.text("Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getDifficulty().toString()).color(NamedTextColor.AQUA)))

        val nether: ItemStack = if(_settings.getNether()){
            _helper.newItem(Material.NETHERRACK, Component.text("Nether ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.NETHERRACK, Component.text("Nether OFF", NamedTextColor.RED))
        }

        val end: ItemStack = if(_settings.getEnd()){
            _helper.newItem(Material.END_STONE, Component.text("End ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.END_STONE, Component.text("End OFF", NamedTextColor.RED))
        }

        val silktouch: ItemStack = if(_settings.getSilktouch()){
            _helper.newItem(Material.GOLDEN_PICKAXE, Component.text("Silktouch ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.GOLDEN_PICKAXE, Component.text("Silktouch OFF", NamedTextColor.RED))
        }

        val pvp: ItemStack = if(_settings.getPvP()){
            _helper.newItem(Material.DIAMOND_SWORD, Component.text("PVP ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.DIAMOND_SWORD, Component.text("PVP OFF", NamedTextColor.RED))
        }

        val keepInventory: ItemStack = if(_settings.getKeepInventory()){
            _helper.newItem(Material.CHEST, Component.text("KeepInventory ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.CHEST, Component.text("KeepInventory OFF", NamedTextColor.RED))
        }

        val enchanting: ItemStack = if(_settings.getEnchanting()){
            _helper.newItem(Material.ENCHANTED_BOOK, Component.text("Enchanting ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.ENCHANTED_BOOK, Component.text("Enchanting OFF", NamedTextColor.RED))
        }

        val mobs: ItemStack = if(_settings.getMobs()){
            _helper.newItem(Material.ZOMBIE_HEAD, Component.text("Mobs ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.ZOMBIE_HEAD, Component.text("Mobs OFF", NamedTextColor.RED))
        }

        val bioms: ItemStack = if(_settings.getBioms()){
            _helper.newItem(Material.GRASS_BLOCK, Component.text("Bioms ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.GRASS_BLOCK, Component.text("Bioms OFF", NamedTextColor.RED))
        }

        val achievements: ItemStack = if(_settings.getAchievements()){
            _helper.newItem(Material.ENCHANTED_GOLDEN_APPLE, Component.text("Achievements ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.ENCHANTED_GOLDEN_APPLE, Component.text("Achievements OFF", NamedTextColor.RED))
        }

        val hunger: ItemStack = if(_settings.getHunger()){
            _helper.newItem(Material.ROTTEN_FLESH, Component.text("Hunger ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.ROTTEN_FLESH, Component.text("Hunger OFF", NamedTextColor.RED))
        }

        val mode: ItemStack = if(_settings.getMode() == Mode.NORMAL){
            _helper.newItem(Material.IRON_BLOCK, Component.text(_settings.getMode().toString(),NamedTextColor.AQUA).append(Component.text(" Bingo", NamedTextColor.GOLD)))
        }else if(_settings.getMode() == Mode.ROWS){
            _helper.newItem(Material.GOLD_BLOCK, Component.text(_settings.getMode().toString(),NamedTextColor.AQUA).append(Component.text(" Bingo", NamedTextColor.GOLD)))
        }else {
            _helper.newItem(Material.DIAMOND_BLOCK, Component.text(_settings.getMode().toString(),NamedTextColor.AQUA).append(Component.text(" Bingo", NamedTextColor.GOLD)))
        }

        for (j in 0 until 45) {
            _inventory.setItem(j, empty)
        }

        _inventory.setItem(10, teamsize)
        _inventory.setItem(11, pvp)
        _inventory.setItem(12, difficulty)
        _inventory.setItem(13, keepInventory)
        _inventory.setItem(14, hunger)
        _inventory.setItem(19, bingoDifficulty)
        _inventory.setItem(20, quantity)
        _inventory.setItem(21, enchanting)
        _inventory.setItem(22, mobs)
        _inventory.setItem(23, bioms)
        _inventory.setItem(24, achievements)
        _inventory.setItem(28, nether)
        _inventory.setItem(29, end)
        _inventory.setItem(30, silktouch)
        _inventory.setItem(34, mode)
        _inventory.setItem(44, confirm)
    }

    override fun getInventory(): Inventory {
        return _inventory
    }

    override fun getItem(): ItemStack{
        return _item
    }
}
