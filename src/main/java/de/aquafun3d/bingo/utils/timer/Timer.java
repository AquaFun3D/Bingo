package de.aquafun3d.bingo.utils.timer;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;

public class Timer implements ITimer{

    private final IHelpers _helper;
    private int _lastSec;
    private int _sec;

    public Timer(Plugin plugin, IHelpers helper){
        _sec = 0;
        _lastSec = 0;
        _helper = helper;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,() -> {
            if(_helper.isBingoRunning() && _lastSec != LocalDateTime.now().getSecond()){
                _lastSec = LocalDateTime.now().getSecond();
                _sec++;
            }
            if(_helper.isBingoRunning()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(Component.text(getTimerString()));
                }
            }else{
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(Component.text(ChatColor.GREEN + "" + ChatColor.ITALIC + "Timer paused"));
                }
            }
        },5L,5L);
    }

    public void reset(){
        _helper.changeBingoRunning(false);
        _sec = 0;
    }

    private String getTimerString(){
        String hms;
        if(_sec < 3600){
            hms = String.format("%02d:%02d", _sec / 60, _sec % 60);
        }else if(_sec < 3600 * 24){
            hms = String.format("%02d:%02d:%02d", _sec / 60 / 60 , _sec / 60 % 60, _sec % 60);
        }else {
            hms = String.format("%d Days, %02d:%02d:%02d", _sec / 60 / 60 / 24, _sec / 60 / 60 , _sec / 60 % 60, _sec % 60);
        }
        hms = ChatColor.GOLD + "" + hms;
        return hms;
    }
}
