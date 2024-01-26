package de.aquafun3d.bingo.utils.tasks

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemTask(material: Material): IBingoTask {

    private var _itemStack: ItemStack = ItemStack(material)
    private var _type: TaskType = TaskType.ITEM

    override fun getItemStack(): ItemStack {
        return _itemStack
    }

    override fun getTaskType(): TaskType {
        return _type
    }

}