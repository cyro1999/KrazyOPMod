package me.StevenLawson.TotalFreedomMod.Listener;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
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

        if (TFM_BanManager.isIpBanned(ip))
        {
            event.setMotd(ChatColor.RED + TFM_Util.getPlayerFromIp(ip) + ", you are banned.");
            return;
        }
        
        if (TFM_ConfigEntry.ADMIN_ONLY_MODE.getBoolean())
        {
            event.setMotd(ChatColor.RED + "KrazyOP is currently closed.");
            return;
        }
        
        if (TFM_ConfigEntry.TRAINING_SESSION.getBoolean())
        {
            event.setMotd(ChatColor.RED + "KrazyOP is currently in a training session.");
            return;
        }
        
        if (Bukkit.hasWhitelist())
        {
            event.setMotd(ChatColor.RED + "Whitelist is currently enabled.");
            return;
        }
        
        if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())
        {
            event.setMotd(ChatColor.RED + "KrazyOP is currently full.");
            return;
        }
        
        // Colorful MOTD
        String message = String.format("Welcome to " + TFM_Util.randomChatColor() + TFM_ConfigEntry.SERVER_NAME.getString() + TFM_Util.randomChatColor() + "%s" + TFM_Util.randomChatColor() + "!" + TFM_Util.randomChatColor() + " - Fun, Free and Easy! \nRunning on Spigot for Minecraft 1.8.7!", TFM_Util.getPlayerFromIp(ip));

        final StringBuilder motd = new StringBuilder();

        for (String word : message.split(" "))
        {
            motd.append(TFM_Util.randomChatColor()).append(word).append(" ");
        }

        event.setMotd(motd.toString());
    }
}