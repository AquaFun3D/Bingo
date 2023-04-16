package de.aquafun3d.bingo.Listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class NewItemListener implements Listener {


    public NewItemListener(){

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Teamselect"))){
            return;
        }
        if (e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Bingo"))){
            e.setCancelled(true);
            return;
        }
        if (item == null) {
            return;
        }
        if (item.getItemMeta() == null) {
            return;
        }
        checkItem(player, item);
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player player) {
            ItemStack item = e.getItem().getItemStack();
            checkItem(player, item);
        }
    }

    public void checkItem(Player player, ItemStack item){
        //TODO
    }

    public void remove(Player player, ItemStack item){
        //TODO
    }
}
