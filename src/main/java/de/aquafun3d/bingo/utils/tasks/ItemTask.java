package de.aquafun3d.bingo.utils.tasks;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemTask implements IBingoTask{

    private ItemStack _item;

    public ItemTask(Material mat){
        _item = new ItemStack(mat);
    }
    public ItemTask(Material mat, Enchantment enchantment, int level){
        _item = new ItemStack(mat);
        ItemMeta meta = _item.getItemMeta();
        meta.addEnchant(enchantment,level,false);
        _item.setItemMeta(meta);
    }

    public ItemStack getItem(){
        return _item;
    }

}
