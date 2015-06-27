package com.Cyro1999.KrazyOPMod.Listeners;

import com.Cyro1999.KrazyOPMod.KOM_Messages;
import com.Cyro1999.KrazyOPMod.KOM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class KOM_PlayerListener implements Listener
{
    
    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoinEvent(PlayerJoinEvent event)
    {

        Player player = event.getPlayer();

        if (TFM_AdminList.isSuperAdmin(player) && !player.getName().equalsIgnoreCase("varuct"))
        {
            TFM_PlayerData.getPlayerData(player).setCommandSpy(true);
        }
        if (KOM_Util.FAMOUS.contains(player.getName().toLowerCase()))
        {
            player.setPlayerListName("[Fake]" + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
            TFM_Util.bcastMsg(":WARNING: " + player.getName() + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
        }
        else if (KOM_Util.SYSTEMADMINS.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&2System Administrator&8]");
        }
        else if (KOM_Util.OWNERS.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.DARK_RED + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&4Owner&8]");
        }
        else if (KOM_Util.EXECUTIVES.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.BLUE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&9Executive&8]");
        }
        else if (KOM_Util.DEVELOPERS.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Developer&8]");
        }
        else if (TFM_AdminList.isSeniorAdmin(player))
        {
            player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&dSenior Admin&8]");
        }
        else if (TFM_AdminList.isTelnetAdmin(player))
        {
            player.setPlayerListName(ChatColor.GREEN + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&aTelnet Admin&8]");
        }
        else if (TFM_AdminList.isSuperAdmin(player))
        {
            player.setPlayerListName(ChatColor.AQUA + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&BSuper Admin&8]");
        }
    }
    
        @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        Player player = (Player) event;
        
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
