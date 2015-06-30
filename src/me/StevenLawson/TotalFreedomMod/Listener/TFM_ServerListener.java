package me.StevenLawson.TotalFreedomMod.Listener;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_ServerInterface;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class TFM_ServerListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event)
    {
        final String ip = event.getAddress().getHostAddress();
        event.setMotd(TFM_Util.randomChatColor() + TFM_Util.getPlayerFromIp(ip) + TFM_Util.randomChatColor() + ", welcome to " + "Krazy" + TFM_Util.randomChatColor() + "OP " + ChatColor.DARK_GRAY + "-" + TFM_Util.randomChatColor() + " Running Spigot v" + TFM_ServerInterface.getVersion());

        if (TFM_BanManager.isIpBanned(ip))
        {
            event.setMotd(ChatColor.RED + TFM_Util.getPlayerFromIp(ip) +  ", you are banned.");
        }
        
        else if (TFM_ConfigEntry.ADMIN_ONLY_MODE.getBoolean())
        {
            event.setMotd(ChatColor.RED + "Server is closed.");
        }
        
        else if (Bukkit.hasWhitelist())
        {
            event.setMotd(ChatColor.RED + "Whitelist enabled.");
        }
        
        else if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())
        {
            event.setMotd(ChatColor.RED + "Server is full.");
        }
        
    }
}