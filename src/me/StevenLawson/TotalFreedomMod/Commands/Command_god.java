package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Changes god mode", usage = "/<command> [player]", aliases = "/egod")
public class Command_god extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            TFM_Util.setGod(sender_p, !TFM_Util.inGod(sender_p));
            sender.sendMessage(ChatColor.GRAY + "God mode set to " + TFM_Util.inGod(sender_p));
        }
        if (args.length == 1 && TFM_AdminList.isSuperAdmin(sender))
        {
            Player player = null;
            player = getPlayer(args[0]);
            if (player == null)
            {
                sender.sendMessage(ChatColor.RED + TFM_Command.PLAYER_NOT_FOUND);
            }
            else
            {
                TFM_Util.setGod(player, !TFM_Util.inGod(player));
                sender.sendMessage(ChatColor.GRAY + "God mode of " + player.getName() + " set to " + TFM_Util.inGod(player));
                player.sendMessage(ChatColor.RED + "Your god mode was set to " + TFM_Util.inGod(player) + " by " + sender.getName());
            }
        }
        return true;
    }
}
