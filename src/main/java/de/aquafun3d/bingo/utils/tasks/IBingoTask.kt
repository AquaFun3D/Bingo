package de.aquafun3d.bingo.utils.tasks

import org.bukkit.inventory.ItemStack

interface IBingoTask {

    fun getItemStack(): ItemStack
    fun getTaskType(): TaskType
}