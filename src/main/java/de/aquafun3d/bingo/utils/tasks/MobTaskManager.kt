package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings
import org.bukkit.Material
import org.bukkit.entity.EntityType

class MobTaskManager(private val _settings: ISettings): IMobTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private var _listReturn = mutableListOf<IBingoTask>()

    override fun getMobs(amount: Int): List<IBingoTask> {
        _listReturn.clear()
        if(_list.isEmpty()) {
            when (_settings.getBingoDifficulty()) {
                TaskDifficulty.EASY -> {
                    overworldEasy()
                    if (_settings.getNether()) netherEasy()
                }

                TaskDifficulty.NORMAL -> {
                    overworldEasy()
                    if (_settings.getNether()) netherEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    if (_settings.getEnd()) end()
                }

                TaskDifficulty.HARD -> {
                    overworldEasy()
                    if (_settings.getNether()) netherEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    overworldHard()
                    if (_settings.getNether()) netherHard()
                    if (_settings.getEnd()) end()
                }

                TaskDifficulty.EXTREME -> {
                    overworldEasy()
                    if (_settings.getNether()) netherEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    overworldHard()
                    if (_settings.getNether()) netherHard()
                    if (_settings.getEnd()) end()
                    extreme()
                }
            }
        }
        _list.shuffle()
        var index = 0
        while (index < amount){
            val rdm = (0 until _list.size).random()
            _listReturn.add(_list[rdm])
            _list.removeAt(rdm)
            index++
        }
        return _listReturn
    }

    private fun overworldEasy(){
        _list.add(MobTask(Material.COW_SPAWN_EGG, "Cow", EntityType.COW))
        _list.add(MobTask(Material.CHICKEN_SPAWN_EGG, "Chicken", EntityType.CHICKEN))
        _list.add(MobTask(Material.COD_SPAWN_EGG, "Cod", EntityType.COD))
        _list.add(MobTask(Material.PIG_SPAWN_EGG, "Pig", EntityType.PIG))
        _list.add(MobTask(Material.SALMON, "Salmon", EntityType.SALMON))
        _list.add(MobTask(Material.SHEEP_SPAWN_EGG, "Sheep", EntityType.SHEEP))
        _list.add(MobTask(Material.SQUID_SPAWN_EGG, "Squid", EntityType.SQUID))
        _list.add(MobTask(Material.SPIDER_SPAWN_EGG, "Spider", EntityType.SPIDER))
        _list.add(MobTask(Material.CREEPER_SPAWN_EGG, "Creeper", EntityType.CREEPER))
        _list.add(MobTask(Material.SKELETON_SPAWN_EGG, "Skeleton", EntityType.SKELETON))
        _list.add(MobTask(Material.ZOMBIE_SPAWN_EGG, "Zombie", EntityType.ZOMBIE))
    }

    private fun overworldNormal(){
        _list.add(MobTask(Material.AXOLOTL_SPAWN_EGG, "Axolotl", EntityType.AXOLOTL))
        _list.add(MobTask(Material.BAT_SPAWN_EGG, "Bat", EntityType.BAT))
        _list.add(MobTask(Material.CAMEL_SPAWN_EGG, "Camel", EntityType.CAMEL))
        _list.add(MobTask(Material.CAT_SPAWN_EGG, "Cat", EntityType.CAT))
        _list.add(MobTask(Material.HORSE_SPAWN_EGG, "Horse", EntityType.HORSE))
        _list.add(MobTask(Material.PUFFERFISH, "Pufferfish", EntityType.PUFFERFISH))
        _list.add(MobTask(Material.RABBIT_SPAWN_EGG, "Rabbit", EntityType.RABBIT))
        _list.add(MobTask(Material.SNOW_GOLEM_SPAWN_EGG, "Snow Golem", EntityType.SNOWMAN))
        _list.add(MobTask(Material.TROPICAL_FISH_SPAWN_EGG, "Tropical Fish", EntityType.TROPICAL_FISH))
        _list.add(MobTask(Material.TURTLE_SPAWN_EGG, "Turtle", EntityType.TURTLE))
        _list.add(MobTask(Material.VILLAGER_SPAWN_EGG, "Villager", EntityType.VILLAGER))
        _list.add(MobTask(Material.BEE_SPAWN_EGG, "Bee", EntityType.BEE))
        _list.add(MobTask(Material.DOLPHIN_SPAWN_EGG, "Dolphin", EntityType.DOLPHIN))
        _list.add(MobTask(Material.DROWNED_SPAWN_EGG, "Drowned", EntityType.DROWNED))
        _list.add(MobTask(Material.ENDERMAN_SPAWN_EGG, "Enderman", EntityType.ENDERMAN))
        _list.add(MobTask(Material.IRON_GOLEM_SPAWN_EGG, "Iron Golem", EntityType.IRON_GOLEM))
        _list.add(MobTask(Material.WOLF_SPAWN_EGG, "Wolf", EntityType.WOLF))
        _list.add(MobTask(Material.HUSK_SPAWN_EGG, "Husk", EntityType.HUSK))
        _list.add(MobTask(Material.PILLAGER_SPAWN_EGG, "Pillager", EntityType.PILLAGER))
    }

    private fun overworldHard(){
        _list.add(MobTask(Material.ALLAY_SPAWN_EGG, "Allay", EntityType.ALLAY))
        _list.add(MobTask(Material.DONKEY_SPAWN_EGG, "Donkey", EntityType.DONKEY))
        _list.add(MobTask(Material.FROG_SPAWN_EGG, "Frog", EntityType.FROG))
        _list.add(MobTask(Material.GLOW_SQUID_SPAWN_EGG, "Glow Squid", EntityType.GLOW_SQUID))
        _list.add(MobTask(Material.MOOSHROOM_SPAWN_EGG, "Mushroom Cow", EntityType.MUSHROOM_COW))
        _list.add(MobTask(Material.MULE_SPAWN_EGG, "Mule", EntityType.MULE))
        _list.add(MobTask(Material.OCELOT_SPAWN_EGG, "Ocelot", EntityType.OCELOT))
        _list.add(MobTask(Material.PARROT_SPAWN_EGG, "Parrot", EntityType.PARROT))
        _list.add(MobTask(Material.TADPOLE_SPAWN_EGG, "Tadpole", EntityType.TADPOLE))
        _list.add(MobTask(Material.CAVE_SPIDER_SPAWN_EGG, "Cave Spider", EntityType.CAVE_SPIDER))
        _list.add(MobTask(Material.FOX_SPAWN_EGG, "Fox", EntityType.FOX))
        _list.add(MobTask(Material.GOAT_SPAWN_EGG, "Goat", EntityType.GOAT))
        _list.add(MobTask(Material.LLAMA_SPAWN_EGG, "Llama", EntityType.LLAMA))
        _list.add(MobTask(Material.PANDA_SPAWN_EGG, "Panda", EntityType.PANDA))
        _list.add(MobTask(Material.POLAR_BEAR_SPAWN_EGG, "Polar Bear", EntityType.POLAR_BEAR))
        _list.add(MobTask(Material.EVOKER_SPAWN_EGG, "Evoker", EntityType.EVOKER))
        _list.add(MobTask(Material.GUARDIAN_SPAWN_EGG, "Guardian", EntityType.GUARDIAN))
        _list.add(MobTask(Material.RAVAGER_SPAWN_EGG, "Ravager", EntityType.RAVAGER))
        _list.add(MobTask(Material.SILVERFISH_SPAWN_EGG, "Silverfish", EntityType.SILVERFISH))
        _list.add(MobTask(Material.SLIME_SPAWN_EGG, "Slime", EntityType.SLIME))
        _list.add(MobTask(Material.STRAY_SPAWN_EGG, "Stray", EntityType.STRAY))
        _list.add(MobTask(Material.VEX_SPAWN_EGG, "Vex", EntityType.VEX))
        _list.add(MobTask(Material.VINDICATOR_SPAWN_EGG, "Vindicator", EntityType.VINDICATOR))
        _list.add(MobTask(Material.WITCH_SPAWN_EGG, "Witch", EntityType.WITCH))
        _list.add(MobTask(Material.ZOMBIE_VILLAGER_SPAWN_EGG, "Zomie Villager", EntityType.ZOMBIE_VILLAGER))
        _list.add(MobTask(Material.ELDER_GUARDIAN_SPAWN_EGG, "Elder Guardian", EntityType.ELDER_GUARDIAN))
    }

    private fun extreme(){
        _list.add(MobTask(Material.SKELETON_HORSE_SPAWN_EGG, "Skeleton Horse", EntityType.SKELETON_HORSE))
        _list.add(MobTask(Material.SNIFFER_SPAWN_EGG, "Sniffer", EntityType.SNIFFER))
        _list.add(MobTask(Material.WANDERING_TRADER_SPAWN_EGG, "Wandering Trader", EntityType.WANDERING_TRADER))
        _list.add(MobTask(Material.TRADER_LLAMA_SPAWN_EGG, "Trader LLama", EntityType.TRADER_LLAMA))
        _list.add(MobTask(Material.ENDERMITE_SPAWN_EGG, "Endermite", EntityType.ENDERMITE))
        _list.add(MobTask(Material.PHANTOM_SPAWN_EGG, "Phantom", EntityType.PHANTOM))
        _list.add(MobTask(Material.WARDEN_SPAWN_EGG, "Warden", EntityType.WARDEN))
        _list.add(MobTask(Material.WITHER_SPAWN_EGG, "Wither", EntityType.WITHER))
    }

    private fun netherEasy(){
        _list.add(MobTask(Material.PIGLIN_SPAWN_EGG, "Piglin", EntityType.PIGLIN))
        _list.add(MobTask(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, "Zombiefied Piglin", EntityType.ZOMBIFIED_PIGLIN))
        _list.add(MobTask(Material.HOGLIN_SPAWN_EGG, "Hoglin", EntityType.HOGLIN))
        _list.add(MobTask(Material.MAGMA_CUBE_SPAWN_EGG, "Magma Cube", EntityType.MAGMA_CUBE))
    }

    private fun netherNormal(){
        _list.add(MobTask(Material.STRIDER_SPAWN_EGG, "Strider", EntityType.STRIDER))
        _list.add(MobTask(Material.BLAZE_SPAWN_EGG, "Blaze", EntityType.BLAZE))
        _list.add(MobTask(Material.GHAST_SPAWN_EGG, "Ghast", EntityType.GHAST))
        _list.add(MobTask(Material.WITHER_SKELETON_SPAWN_EGG, "Wither Skeleton", EntityType.WITHER_SKELETON))
        _list.add(MobTask(Material.ZOGLIN_SPAWN_EGG, "Zoglin", EntityType.ZOGLIN))
    }

    private fun netherHard(){
        _list.add(MobTask(Material.PIGLIN_BRUTE_SPAWN_EGG, "Piglin Brute", EntityType.PIGLIN_BRUTE))
    }

    private fun end(){
        _list.add(MobTask(Material.SHULKER_SPAWN_EGG, "Shulker", EntityType.SHULKER))
        _list.add(MobTask(Material.ENDER_DRAGON_SPAWN_EGG , "Ender Dragon", EntityType.ENDER_DRAGON))
    }
}