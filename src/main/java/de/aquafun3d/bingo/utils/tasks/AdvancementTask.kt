package de.aquafun3d.bingo.utils.tasks

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.advancement.Advancement
import org.bukkit.block.Biome
import org.bukkit.inventory.ItemStack

class AdvancementTask(material: Material, name: String, advancement: Advancement): IBingoTask {

    private var _itemStack: ItemStack = ItemStack(material)
    private var _type: TaskType = TaskType.MOB
    private var _name: Component = Component.text(name)
    private var _advancement: Advancement = advancement

    init {
        val meta = _itemStack.itemMeta
        meta.displayName(Component.text(name, NamedTextColor.YELLOW))
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

    fun getAdvancement(): Advancement {
        return _advancement
    }
}