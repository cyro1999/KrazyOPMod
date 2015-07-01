package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.BOTH)
@CommandParameters(
        description = "SeniorAdminChat - Talk privately with other Senior Admins. Using <command> itself will toggle SeniorAdminChat on and off for all messages.",
        usage = "/<command> [message...]",
        aliases = "senioradminchat")
public class Command_p extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                playerMsg("Only in-game players can toggle SeniorAdminChat.");
                return true;
            }
            
            final TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(sender_p);
            
            if (playerdata.inAdminChat()) {
                sender.sendMessage(ChatColor.RED + "Since you had regular admin chat on, we turned it off for you.");
                Bukkit.dispatchCommand(sender, "adminchat");
                TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);
                userinfo.setSeniorAdminChat(!userinfo.inSeniorAdminChat());
                playerMsg("Toggled Senior Admin Chat " + (userinfo.inSeniorAdminChat() ? "on" : "off") + ".");
                return true;
            }

            TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);
            userinfo.setSeniorAdminChat(!userinfo.inSeniorAdminChat());
            playerMsg("Toggled Senior Admin Chat " + (userinfo.inSeniorAdminChat() ? "on" : "off") + ".");
        }
        else
        {
            TFM_Util.SeniorAdminChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }

        return true;
    }
}