package com.Cyro1999.KrazyOPMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(
        description = "Rename the current item in your hand.",
        usage = "/<command> <name>",
        aliases = "rn")
public class Command_rename extends KOM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        String itemRaw = StringUtils.join(args);
        String itemName = TFM_Util.colorize(itemRaw.trim());
        ItemStack i = sender_p.getItemInHand();
        if (i != null)
        {
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(itemName);
            i.setItemMeta(im);
        }
        return true;
    }
}