/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Cyro1999.KrazyOPMod.Listeners;

import com.Cyro1999.KrazyOPMod.KOM_Messages;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Transport
 */
public class KOM_ChatListener implements Listener
{
      @EventHandler
     public void onPlayerChat(AsyncPlayerChatEvent e) {
         for(String word : e.getMessage().split(" ")){
              if(TotalFreedomMod.plugin.config.getStringList("bannedwords").contains(word)){
              e.setCancelled(true);
              e.getPlayer().sendMessage(KOM_Messages.MODTAG + ChatColor.DARK_RED + "The word " + word + " is banned!");           
              }
         }
    
}
}