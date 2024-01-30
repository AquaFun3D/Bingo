package de.aquafun3d.bingo.utils.tasks

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.block.Biome
import org.bukkit.inventory.ItemStack

class BiomeTask(name: String, biome: Biome): IBingoTask {

    private var _itemStack: ItemStack = ItemStack(Material.REINFORCED_DEEPSLATE)
    private var _type: TaskType = TaskType.BIOME
    private var _name: Component = Component.text(name)
    private var _biome: Biome = biome

    init {
        val meta = _itemStack.itemMeta
        meta.displayName(Component.text(name, NamedTextColor.RED))
        _itemStack.setItemMeta(meta)
    }

    override fun getItemStack(): ItemStack {
        return _itemStack
    }

    override fun getTaskType(): TaskType {
        return _type
    }

    fun getName(): Component {
        return _name
    }

    fun getBiome(): Biome {
        return _biome
    }
}