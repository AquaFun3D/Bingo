package de.aquafun3d.bingo.utils.tasks

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

class ItemTask : IBingoTask {
    var item: ItemStack
        private set

    constructor(mat: Material?) {
        item = ItemStack(mat!!)
    }

    constructor(mat: Material?, enchantment: Enchantment?, level: Int) {
        item = ItemStack(mat!!)
        val meta = item.itemMeta
        meta.addEnchant(enchantment!!, level, false)
        item.setItemMeta(meta)
    }
}
