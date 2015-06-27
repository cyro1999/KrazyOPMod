package com.Cyro1999.KrazyOPMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(
        description = "See how to become admin.",
        aliases = "ai",
        usage = "/<command>")
public class Command_admininfo extends KOM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (TFM_ConfigEntry.APPLICATIONS_ENABLED.getBoolean()) {
            playerMsg("So you wish to apply for admin do you? Apply on the forums @ http://krazyop.boards.net/", ChatColor.GREEN);
        }
        else {
            playerMsg("So you wish to apply for admin do you? Well, I'm sorry. You may not apply at the moment.", ChatColor.BLUE);
        }
        return true;
    }

}
