/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cyro1999.KrazyOPMod;

import com.Cyro1999.KrazyOPMod.Commands.KOM_CommandHandler;
import me.StevenLawson.TotalFreedomMod.Commands.TFM_CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author tyler
 */
public class KOM_Switcher
{
    public static boolean handleCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        KOM_CommandHandler.handleCommand(sender, cmd, commandLabel, args);
        TFM_CommandHandler.handleCommand(sender, cmd, commandLabel, args);
        return true;
    }
}
