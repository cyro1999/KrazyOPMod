package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(
        description = "AdminChat - Talk privately with other admins. Using <command> itself will toggle AdminChat on and off for all messages.",
        usage = "/<command> [message...]",
        aliases = "adminchat")
public class Command_o extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                playerMsg("Only in-game players can toggle AdminChat.");
                return true;
            }
            
            final TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(sender_p);
            
            if (playerdata.inSeniorAdminChat()) {
                sender.sendMessage(ChatColor.RED + "Since you had Senior Admin Chat on, we turned it off for you.");
                Bukkit.dispatchCommand(sender, "senioradminchat");
                TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);
                userinfo.setAdminChat(!userinfo.inAdminChat());
                playerMsg("Toggled Admin Chat " + (userinfo.inAdminChat() ? "on" : "off") + ".");
                return true;
            }

            TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);
            userinfo.setAdminChat(!userinfo.inAdminChat());
            playerMsg("Toggled Admin Chat " + (userinfo.inAdminChat() ? "on" : "off") + ".");
        }
        else
        {
            TFM_Util.adminChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }

        return true;
    }
}
