package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import org.bukkit.Material

class AdvancementTaskManager(private val _settings: ISettings, private val _helpers: IHelpers): IAdvancementTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private var _listReturn = mutableListOf<IBingoTask>()

    override fun getAdvancements(amount: Int): List<IBingoTask> {
        _listReturn.clear()
        if(_list.isEmpty()) {
            when (_settings.getBingoDifficulty()) {
                TaskDifficulty.EASY -> {
                    overworldEasy()
                    if (_settings.getEnd()) endEasy()
                }

                TaskDifficulty.NORMAL -> {
                    overworldEasy()
                    if (_settings.getEnd()) endEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    if (_settings.getEnd()) endNormal()
                }

                TaskDifficulty.HARD -> {
                    overworldEasy()
                    if (_settings.getEnd()) endEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    if (_settings.getEnd()) endNormal()
                    overworldHard()
                    if (_settings.getNether()) netherHard()
                    if (_settings.getEnd()) endHard()
                }

                TaskDifficulty.EXTREME -> {
                    overworldEasy()
                    if (_settings.getEnd()) endEasy()
                    overworldNormal()
                    if (_settings.getNether()) netherNormal()
                    if (_settings.getEnd()) endNormal()
                    overworldHard()
                    if (_settings.getNether()) netherHard()
                    if (_settings.getEnd()) endHard()
                    extreme()
                }
            }
        }
        _list.shuffle()
        var index = 0
        while (index < amount){
            if(_list.isEmpty()) break
            val rdm = (0 until _list.size).random()
            _listReturn.add(_list[rdm])
            _list.removeAt(rdm)
            index++
        }
        return _listReturn
    }

    private fun overworldEasy(){
        _list.add(AdvancementTask(Material.WOODEN_PICKAXE, "Stone Age", _helpers.parse("story/mine_stone")))
        _list.add(AdvancementTask(Material.STONE_PICKAXE, "Getting an Upgrade", _helpers.parse("story/upgrade_tools")))
        _list.add(AdvancementTask(Material.IRON_INGOT, "Acquire Hardware", _helpers.parse("story/smelt_iron")))
        _list.add(AdvancementTask(Material.IRON_CHESTPLATE, "Suit Up", _helpers.parse("story/obtain_armor")))
        _list.add(AdvancementTask(Material.LAVA_BUCKET, "Hot Stuff", _helpers.parse("story/lava_bucket")))
        _list.add(AdvancementTask(Material.IRON_PICKAXE, "Isn't It Iron Pick", _helpers.parse("story/iron_tools")))
        _list.add(AdvancementTask(Material.SHIELD, "Not Today, Thank You", _helpers.parse("story/deflect_arrow")))
        _list.add(AdvancementTask(Material.IRON_SWORD, "Monster Hunter", _helpers.parse("adventure/kill_a_mob")))
        _list.add(AdvancementTask(Material.EMERALD, "What a Deal!", _helpers.parse("adventure/trade")))
        _list.add(AdvancementTask(Material.RED_BED, "Sweet Dreams", _helpers.parse("adventure/sleep_in_bed")))
        _list.add(AdvancementTask(Material.BOW, "Take Aim", _helpers.parse("adventure/shoot_arrow")))
        _list.add(AdvancementTask(Material.WHEAT, "The Parrots and the Bats", _helpers.parse("husbandry/breed_an_animal")))
        _list.add(AdvancementTask(Material.LEAD, "Best Friends Forever", _helpers.parse("husbandry/tame_an_animal")))
        _list.add(AdvancementTask(Material.FISHING_ROD, "Fishy Business", _helpers.parse("husbandry/fishy_business")))
        _list.add(AdvancementTask(Material.WHEAT_SEEDS, "A Seedy Place", _helpers.parse("husbandry/plant_seed")))

    }

    private fun overworldNormal(){
        _list.add(AdvancementTask(Material.OBSIDIAN, "Ice Bucket Challenge", _helpers.parse("story/form_obsidian")))
        _list.add(AdvancementTask(Material.DIAMOND, "Diamonds!", _helpers.parse("story/mine_diamond")))
        _list.add(AdvancementTask(Material.FLINT_AND_STEEL, "We Need to Go Deeper", _helpers.parse("story/enter_the_nether")))
        _list.add(AdvancementTask(Material.DIAMOND_CHESTPLATE, "Cover Me with Diamonds", _helpers.parse("story/shiny_gear")))
        _list.add(AdvancementTask(Material.BLACK_BANNER, "Voluntary Exile", _helpers.parse("adventure/voluntary_exile")))
        _list.add(AdvancementTask(Material.SPYGLASS, "Is It a Bird?", _helpers.parse("adventure/spyglass_at_parrot")))
        _list.add(AdvancementTask(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, "Crafting a New Look", _helpers.parse("adventure/trim_with_any_armor_pattern")))
        _list.add(AdvancementTask(Material.CROSSBOW, "Ol' Betsy", _helpers.parse("adventure/ol_betsy")))
        _list.add(AdvancementTask(Material.WATER_BUCKET, "Caves & Cliffs", _helpers.parse("adventure/fall_from_world_height")))
        _list.add(AdvancementTask(Material.BRUSH, "Respecting the Remnants", _helpers.parse("adventure/salvage_sherd")))
        _list.add(AdvancementTask(Material.JACK_O_LANTERN, "Hired Help", _helpers.parse("adventure/summon_iron_golem")))
        _list.add(AdvancementTask(Material.HONEY_BOTTLE, "Bee Our Guest", _helpers.parse("husbandry/safely_harvest_honey")))
        _list.add(AdvancementTask(Material.GLOW_INK_SAC, "Glow and Behold!", _helpers.parse("husbandry/make_a_sign_glow")))
        _list.add(AdvancementTask(Material.HONEYCOMB, "Wax On", _helpers.parse("husbandry/wax_on")))
        _list.add(AdvancementTask(Material.PUFFERFISH_BUCKET, "Tactical Fishing", _helpers.parse("husbandry/tactical_fishing")))
        _list.add(AdvancementTask(Material.STONE_AXE, "Wax Off", _helpers.parse("husbandry/wax_off")))

    }

    private fun overworldHard(){
        _list.add(AdvancementTask(Material.HONEY_BLOCK, "Sticky Situation", _helpers.parse("adventure/honey_block_slide")))
        _list.add(AdvancementTask(Material.SCULK_SENSOR, "Sneak 100", _helpers.parse("adventure/avoid_vibration")))
        _list.add(AdvancementTask(Material.BLACK_BANNER, "Hero of the Village", _helpers.parse("adventure/hero_of_the_village")))
        _list.add(AdvancementTask(Material.TRIDENT, "A Throwaway Joke", _helpers.parse("adventure/throw_trident")))
        _list.add(AdvancementTask(Material.SCULK_CATALYST, "It Spreads", _helpers.parse("adventure/kill_mob_near_sculk_catalyst")))
        _list.add(AdvancementTask(Material.TOTEM_OF_UNDYING, "Postmortal", _helpers.parse("adventure/totem_of_undying")))
        _list.add(AdvancementTask(Material.CROSSBOW, "Who's the Pillager Now?", _helpers.parse("adventure/whos_the_pillager_now")))
        _list.add(AdvancementTask(Material.CROSSBOW, "Arbalistic", _helpers.parse("adventure/arbalistic")))
        _list.add(AdvancementTask(Material.DECORATED_POT, "Careful Restoration", _helpers.parse("adventure/craft_decorated_pot_using_only_sherds")))
        _list.add(AdvancementTask(Material.JUKEBOX, "Sound of Music", _helpers.parse("adventure/play_jukebox_in_meadows")))
        _list.add(AdvancementTask(Material.LEATHER_BOOTS, "Light as a Rabbit", _helpers.parse("adventure/walk_on_powder_snow_with_leather_boots")))
        _list.add(AdvancementTask(Material.ARROW, "Sniper Duel", _helpers.parse("adventure/sniper_duel")))
        _list.add(AdvancementTask(Material.TARGET, "Bullseye", _helpers.parse("adventure/bullseye")))
        _list.add(AdvancementTask(Material.COOKIE, "You've Got a Friend in Me", _helpers.parse("husbandry/allay_deliver_item_to_player")))
        _list.add(AdvancementTask(Material.OAK_BOAT, "Whatever Floats Your Goat!", _helpers.parse("husbandry/ride_a_boat_with_a_goat")))
        _list.add(AdvancementTask(Material.BEE_NEST, "Total Beelocation", _helpers.parse("husbandry/silk_touch_nest")))
        _list.add(AdvancementTask(Material.TADPOLE_BUCKET, "Bukkit Bukkit", _helpers.parse("husbandry/tadpole_in_a_bucket")))
        _list.add(AdvancementTask(Material.SNIFFER_EGG, "Smells Interesting", _helpers.parse("husbandry/obtain_sniffer_egg")))
        _list.add(AdvancementTask(Material.AXOLOTL_BUCKET, "The Cutest Predator", _helpers.parse("husbandry/axolotl_in_a_bucket")))
        _list.add(AdvancementTask(Material.ENCHANTED_BOOK, "Enchanter", _helpers.parse("story/enchant_item")))
        _list.add(AdvancementTask(Material.GOLDEN_APPLE, "Zombie Doctor", _helpers.parse("story/cure_zombie_villager")))
        _list.add(AdvancementTask(Material.TROPICAL_FISH_BUCKET, "The Healing Power of Friendship!", _helpers.parse("husbandry/kill_axolotl_target")))
    }

    private fun extreme(){
        _list.add(AdvancementTask(Material.MILK_BUCKET, "A Furious Cocktail", _helpers.parse("nether/all_potions")))
        _list.add(AdvancementTask(Material.BEACON, "Beaconator", _helpers.parse("nether/create_full_beacon")))
        _list.add(AdvancementTask(Material.BUCKET, "How Did We Get Here?", _helpers.parse("nether/all_effects")))
        _list.add(AdvancementTask(Material.LIGHTNING_ROD, "Surge Protector", _helpers.parse("adventure/lightning_rod_with_villager_no_fire")))
        _list.add(AdvancementTask(Material.CROSSBOW, "Two Birds, One Arrow", _helpers.parse("adventure/two_birds_one_arrow")))
        _list.add(AdvancementTask(Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing with Style", _helpers.parse("adventure/trim_with_all_exclusive_armor_patterns")))
        _list.add(AdvancementTask(Material.DIAMOND_BOOTS, "Adventuring Time", _helpers.parse("adventure/adventuring_time")))
        _list.add(AdvancementTask(Material.TRIDENT, "Very Very Frightening", _helpers.parse("adventure/very_very_frightening")))
        _list.add(AdvancementTask(Material.GOLDEN_CARROT, "Two by Two", _helpers.parse("husbandry/bred_all_animals")))
        _list.add(AdvancementTask(Material.JUKEBOX, "Birthday Song", _helpers.parse("husbandry/allay_deliver_cake_to_note_block")))
        _list.add(AdvancementTask(Material.COD, "A Complete Catalogue", _helpers.parse("husbandry/complete_catalogue")))
        _list.add(AdvancementTask(Material.LEAD, "When the Squad Hops into Town", _helpers.parse("husbandry/leash_all_frog_variants")))
        _list.add(AdvancementTask(Material.APPLE, "A Balanced Diet", _helpers.parse("husbandry/balanced_diet")))
        _list.add(AdvancementTask(Material.OCHRE_FROGLIGHT, "With Our Powers Combined!", _helpers.parse("husbandry/froglights")))
        _list.add(AdvancementTask(Material.DIAMOND_SWORD, "Monsters Hunted", _helpers.parse("adventure/kill_all_mobs")))
        _list.add(AdvancementTask(Material.EMERALD, "Star Trader", _helpers.parse("adventure/trade_at_world_height")))
    }

    private fun netherNormal(){
        _list.add(AdvancementTask(Material.ENDER_EYE, "Eye Spy", _helpers.parse("story/follow_ender_eye")))
        _list.add(AdvancementTask(Material.FIRE_CHARGE, "Return to Sender", _helpers.parse("nether/return_to_sender")))
        _list.add(AdvancementTask(Material.POLISHED_BLACKSTONE_BRICKS, "Those Were the Days", _helpers.parse("nether/find_bastion")))
        _list.add(AdvancementTask(Material.ANCIENT_DEBRIS, "Hidden in the Depths", _helpers.parse("nether/obtain_ancient_debris")))
        _list.add(AdvancementTask(Material.NETHER_BRICKS, "A Terrible Fortress", _helpers.parse("nether/find_fortress")))
        _list.add(AdvancementTask(Material.CRYING_OBSIDIAN, "Who is Cutting Onions?", _helpers.parse("nether/obtain_crying_obsidian")))
        _list.add(AdvancementTask(Material.GOLD_INGOT, "Oh Shiny", _helpers.parse("nether/distract_piglin")))
        _list.add(AdvancementTask(Material.WARPED_FUNGUS_ON_A_STICK, "This Boat Has Legs", _helpers.parse("nether/ride_strider")))
        _list.add(AdvancementTask(Material.CHEST, "War Pigs", _helpers.parse("nether/loot_bastion")))
        _list.add(AdvancementTask(Material.BLAZE_ROD, "Into Fire", _helpers.parse("nether/obtain_blaze_rod")))
        _list.add(AdvancementTask(Material.NETHERITE_BOOTS, "Hot Tourist Destinations", _helpers.parse("nether/explore_nether")))
        _list.add(AdvancementTask(Material.POTION, "Local Brewery", _helpers.parse("nether/brew_potion")))
        _list.add(AdvancementTask(Material.CHISELED_BOOKSHELF, "The Power of Books", _helpers.parse("adventure/read_power_of_chiseled_bookshelf")))
        _list.add(AdvancementTask(Material.SPYGLASS, "Is It a Balloon?", _helpers.parse("adventure/spyglass_at_ghast")))
    }

    private fun netherHard(){
        _list.add(AdvancementTask(Material.PAPER, "Subspace Bubble", _helpers.parse("nether/fast_travel")))
        _list.add(AdvancementTask(Material.GHAST_TEAR, "Uneasy Alliance", _helpers.parse("nether/uneasy_alliance")))
        _list.add(AdvancementTask(Material.LODESTONE, "Country Lode, Take Me Home", _helpers.parse("nether/use_lodestone")))
        _list.add(AdvancementTask(Material.NETHERITE_CHESTPLATE, "Cover Me in Debris", _helpers.parse("nether/netherite_armor")))
        _list.add(AdvancementTask(Material.WITHER_SKELETON_SKULL, "Spooky Scary Skeleton", _helpers.parse("nether/get_wither_skull")))
        _list.add(AdvancementTask(Material.RESPAWN_ANCHOR, "Not Quite \"Nine\" Lives", _helpers.parse("nether/charge_respawn_anchor")))
        _list.add(AdvancementTask(Material.WARPED_FUNGUS_ON_A_STICK, "Feels Like Home", _helpers.parse("nether/ride_strider_in_overworld_lava")))
        _list.add(AdvancementTask(Material.NETHER_STAR, "Withering Heights", _helpers.parse("nether/summon_wither")))
        _list.add(AdvancementTask(Material.BEACON, "Bring Home the Beacon", _helpers.parse("nether/create_beacon")))
        _list.add(AdvancementTask(Material.NETHERITE_HOE, "Serious Dedication", _helpers.parse("husbandry/obtain_netherite_hoe")))
    }

    private fun endEasy(){
        _list.add(AdvancementTask(Material.END_STONE, "The End?", _helpers.parse("story/enter_the_end")))
        _list.add(AdvancementTask(Material.SPYGLASS, "Is It a Plane?", _helpers.parse("adventure/spyglass_at_dragon")))
    }

    private fun endNormal(){
        _list.add(AdvancementTask(Material.DRAGON_HEAD, "Free the End", _helpers.parse("end/kill_dragon")))
        _list.add(AdvancementTask(Material.DRAGON_EGG, "The Next Generation", _helpers.parse("end/dragon_egg")))
        _list.add(AdvancementTask(Material.ENDER_PEARL, "Remote Getaway", _helpers.parse("end/enter_end_gateway")))
        _list.add(AdvancementTask(Material.DRAGON_BREATH, "You Need a Mint", _helpers.parse("end/dragon_breath")))
        _list.add(AdvancementTask(Material.PURPUR_BLOCK, "The City at the End of the Game", _helpers.parse("end/find_end_city")))
        _list.add(AdvancementTask(Material.ELYTRA, "Sky's the Limit", _helpers.parse("end/elytra")))
        _list.add(AdvancementTask(Material.SHULKER_SHELL, "Great View From Up Here", _helpers.parse("end/levitate")))
    }

    private fun endHard(){
        _list.add(AdvancementTask(Material.END_CRYSTAL, "The End... Again...", _helpers.parse("end/respawn_dragon")))
    }
}