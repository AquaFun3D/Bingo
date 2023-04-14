package de.aquafun3d.bingo.utils.inventories;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeamselectInventory implements IInventory{

    private final Inventory _inv = Bukkit.createInventory(null, 18, Component.text(ChatColor.DARK_PURPLE + "Teamselect"));
    private final IHelpers _helper;
    private final ItemStack _item;

    public TeamselectInventory(IHelpers helper){
        _helper = helper;
        _item = _helper.newItem(Material.EMERALD,ChatColor.DARK_AQUA + "Select Team");
        newInventory();
    }

    private void newInventory(){
        ItemStack empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE," ");
        ItemStack white = _helper.newItem(Material.WHITE_WOOL, ChatColor.WHITE + "Team #1");
        ItemStack orange = _helper.newItem(Material.ORANGE_WOOL, ChatColor.GOLD + "Team #2");
        ItemStack magenta = _helper.newItem(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Team #3");
        ItemStack lightblue = _helper.newItem(Material.LIGHT_BLUE_WOOL, ChatColor.AQUA + "Team #4");
        ItemStack yellow = _helper.newItem(Material.YELLOW_WOOL, ChatColor.YELLOW + "Team #5");
        ItemStack lime = _helper.newItem(Material.LIME_WOOL, ChatColor.GREEN + "Team #6");
        ItemStack pink = _helper.newItem(Material.PINK_WOOL, ChatColor.RED + "Team #7");
        ItemStack gray = _helper.newItem(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Team #8");
        ItemStack lightgray = _helper.newItem(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Team #9");
        ItemStack cyan = _helper.newItem(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Team #10");
        ItemStack purple = _helper.newItem(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Team #11");
        ItemStack blue = _helper.newItem(Material.BLUE_WOOL, ChatColor.BLUE + "Team #12");
        ItemStack brown = _helper.newItem(Material.BROWN_WOOL, ChatColor.GOLD + "Team #13");
        ItemStack green = _helper.newItem(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Team #14");
        ItemStack red = _helper.newItem(Material.RED_WOOL, ChatColor.DARK_RED + "Team #15");
        ItemStack black = _helper.newItem(Material.BLACK_WOOL, ChatColor.DARK_GRAY + "Team #16");
        ItemStack spec = _helper.newItem(Material.GLASS, ChatColor.GRAY + "Spectator");

        _inv.setItem(0, white);
        _inv.setItem(1, orange);
        _inv.setItem(2, magenta);
        _inv.setItem(3, lightblue);
        _inv.setItem(4, yellow);
        _inv.setItem(5, lime);
        _inv.setItem(6, pink);
        _inv.setItem(7, gray);
        _inv.setItem(8, lightgray);
        _inv.setItem(9, cyan);
        _inv.setItem(10, purple);
        _inv.setItem(11, blue);
        _inv.setItem(12, brown);
        _inv.setItem(13, green);
        _inv.setItem(14, red);
        _inv.setItem(15, black);
        _inv.setItem(16, spec);
        _inv.setItem(17, empty);
    }

    public void updateInventory(String name, Player player){
        for(ItemStack item : _inv){
            if(item.displayName().toString().equals(name)){
                ItemMeta meta = item.getItemMeta();
                ArrayList<Component> list = new ArrayList<>();
                for(String p : player.getScoreboard().getTeam(name).getEntries()){
                    list.add(Component.text(p));
                }
                meta.lore(list);
            }
        }
        for( Player p : Bukkit.getOnlinePlayers()){
            if(p.getOpenInventory().title().equals(Component.text(ChatColor.DARK_PURPLE + "Teamselect"))){
                p.openInventory(_inv);
            }
        }
    }

    public Inventory getInventory(){
        return _inv;
    }

    @Override
    public ItemStack getItem() {
        return _item;
    }

}
