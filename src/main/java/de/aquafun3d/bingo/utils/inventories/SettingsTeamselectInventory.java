package de.aquafun3d.bingo.utils.inventories;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsTeamselectInventory implements ISettingsInventory {

    private final IHelpers _helper;
    private final Inventory _inventory;
    private final ItemStack _item;
    private boolean _confirm;

    public SettingsTeamselectInventory(IHelpers helper){
        _helper = helper;
        _item = _helper.newItem(Material.NETHER_STAR, ChatColor.DARK_AQUA + "Settings");
        _inventory = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE + "Bingo Settings"));
        newInventory();
    }

    private void newInventory(){
        ItemStack empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        //TODO
    }

    @Override
    public Inventory getInventory() {
        return _inventory;
    }

    @Override
    public ItemStack getItem() {
        return _item;
    }

    public boolean isConfirmed(){
        return _confirm;
    }
}
