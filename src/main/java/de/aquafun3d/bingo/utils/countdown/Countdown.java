package de.aquafun3d.bingo.utils.countdown;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Countdown implements ICountdown{

    private int _taskid;
    private final Plugin _plugin;

    public Countdown(Plugin plugin){
        _plugin = plugin;
    }

    public void count(int i){
        _taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, new Runnable() {
            int time = i + 1;
            @Override
            public void run() {
                time--;
                //TODO Fades anpassen
                Component mainTitle = Component.text(ChatColor.GREEN + Integer.toString(time));
                Component subTitle = Component.text(ChatColor.GRAY + "seconds until Bingo starts");
                if(time != 0){
                    Audience.audience().showTitle(Title.title(mainTitle,subTitle));
                }else{
                    Audience.audience().showTitle(Title.title(mainTitle,subTitle));
                    Audience.audience().showTitle(Title.title(Component.text(ChatColor.GREEN + "Bingo"),Component.text(ChatColor.GRAY + "has started")));
                    Sound sound = Sound.sound(Key.key(".entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f);
                    Audience.audience().playSound(sound);
                }
                Bukkit.getScheduler().cancelTask(_taskid);
            }
        },0,20);
    }

}
