package com.Cyro1999.KrazyOPMod.Listeners;

import com.Cyro1999.KrazyOPMod.KOM_Messages;
import com.Cyro1999.KrazyOPMod.KOM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Player;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class KOM_PlayerListener implements Listener
{
    
    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();
        final String ip = TFM_Util.getIp(player);
        final TFM_Player playerEntry;
        String name = player.getName();

        if (TFM_AdminList.isSuperAdmin(player)) {
            TFM_PlayerData.getPlayerData(player).setCommandSpy(true);
        }
        
        else if (KOM_Util.FAMOUS.contains(name.toLowerCase())) {
            player.setPlayerListName("[Fake]" + name);
            TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
            TFM_Util.bcastMsg(":WARNING: " + name + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
        }
        
        else if (KOM_Util.SYSTEMADMINS.contains(name)) {
            player.setPlayerListName(ChatColor.DARK_GREEN + name);
            TFM_PlayerData.getPlayerData(player).setTag("&8[&2System Administrator&8]");
        }
        else if (KOM_Util.OWNERS.contains(name)) {
            player.setPlayerListName(ChatColor.DARK_RED + name);
            TFM_PlayerData.getPlayerData(player).setTag("&8[&4Owner&8]");
        }
        else if (KOM_Util.EXECUTIVES.contains(name)) {
            player.setPlayerListName(ChatColor.BLUE + name);
            TFM_PlayerData.getPlayerData(player).setTag("&8[&9Executive&8]");
        }
        else if (KOM_Util.DEVELOPERS.contains(name)) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + name);
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Developer&8]");
        }
        else if (TFM_AdminList.isSuperAdmin(player)) {
            if (TFM_AdminList.isSeniorAdmin(player)) {
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + name);
                TFM_PlayerData.getPlayerData(player).setTag("&8[&dSenior Admin&8]");
            }
            else if (TFM_AdminList.isTelnetAdmin(player, true)) {
                player.setPlayerListName(ChatColor.DARK_GREEN + name);
                TFM_PlayerData.getPlayerData(player).setTag("&8[&2Telnet Admin&8]");
            }
            else {
                player.setPlayerListName(ChatColor.AQUA + name);
                TFM_PlayerData.getPlayerData(player).setTag("&8[&BSuper Admin&8]");
            }
        }
        
        try {
            player.setPlayerListName(StringUtils.substring(name, 0, 16));
        }
        catch (IllegalArgumentException ex) {
        }
    }
    
        @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        Player player = (Player) event.getPlayer();
        
        String command = event.getMessage().toLowerCase().trim();

        if (command.contains("175:") || command.contains("double_plant:"))
        {
            event.setCancelled(true);
            TFM_Util.bcastMsg(KOM_Messages.MODTAG + player.getName() + " just attempted to use the crash item! Deal with them appropriately please!", ChatColor.DARK_RED);
        }

        if (command.contains("&k") && !TFM_AdminList.isSuperAdmin(player))
        {
            event.setCancelled(true);
            TFM_Util.playerMsg(player, KOM_Messages.MODTAG + ChatColor.RED + "You are not permitted to use &k!");
        }
    }
    
}
