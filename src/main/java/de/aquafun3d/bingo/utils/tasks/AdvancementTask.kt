package de.aquafun3d.bingo.utils.tasks

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class AdvancementTask(material: Material, name: String, advancement: Advancement): IBingoTask {

    private var _itemStack: ItemStack = ItemStack(material)
    private var _type: TaskType = TaskType.ACHIEVMENT
    private var _name: Component = Component.text(name)
    private var _advancement: Advancement = advancement

    init {
        val meta = _itemStack.itemMeta
        meta.displayName(Component.text(name, NamedTextColor.LIGHT_PURPLE))
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
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