package de.aquafun3d.bingo.utils.tasks

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class MobTask(material: Material, name: String, entityType: EntityType): IBingoTask {

    private var _itemStack: ItemStack = ItemStack(material)
    private var _type: TaskType = TaskType.MOB
    private var _name: Component = Component.text(name)
    private var _entityType: EntityType = entityType

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

    fun getName(): Component{
        return _name
    }

    fun getEntityType(): EntityType{
        return _entityType
    }
}