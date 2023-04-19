package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.IHelpers
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemTask(material: Material): IBingoTask {

    private var _itemStack: ItemStack
    private var _type: TaskType = TaskType.ITEM

    init {
        _itemStack = ItemStack(material)
    }

    override fun getItemStack(): ItemStack {
        return _itemStack
    }

    override fun getTaskType(): TaskType {
        return _type
    }

}