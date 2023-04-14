package de.aquafun3d.bingo.utils.inventories;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsInventory implements IInventory{

    private final IHelpers _helper;
    private final Inventory _inventory = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE + "Bingo Settings"));
    private final ItemStack _item;

    public SettingsInventory(IHelpers helper){
        _helper = helper;
        _item = _helper.newItem(Material.NETHER_STAR, ChatColor.DARK_AQUA + "Settings");
        newInventory();
    }

    private void newInventory(){
        ItemStack empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        //TODO
    }

    @Override
    public void updateInventory(String name, Player player) {
        player.openInventory(_inventory);
    }

    @Override
    public Inventory getInventory() {
        return _inventory;
    }

    @Override
    public ItemStack getItem() {
        return _item;
    }
}
