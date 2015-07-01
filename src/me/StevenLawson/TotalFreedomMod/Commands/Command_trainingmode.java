package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Close server to non-superadmins.", usage = "/<command> [on | off]", aliases = "tm")
public class Command_trainingmode extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        if (args[0].equalsIgnoreCase("off -s"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(false);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            return true;
        }

        if (args[0].equalsIgnoreCase("off"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(false);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_Util.adminAction(sender.getName(), "Stopping the Training Mode Session...", true);
            return true;
        }
        else if (args[0].equalsIgnoreCase("on"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(true);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(true);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(true);
            TFM_Util.adminAction(sender.getName(), "Starting the Training Mode Session...", true);
            for (Player player : server.getOnlinePlayers())
            {
                if (!TFM_AdminList.isSuperAdmin(player))
                {
                    player.kickPlayer(TFM_ConfigEntry.SERVER_NAME.getString() + " is now in a Training Session.");
                }
            }
            return true;
        }

        return false;
    }
}
