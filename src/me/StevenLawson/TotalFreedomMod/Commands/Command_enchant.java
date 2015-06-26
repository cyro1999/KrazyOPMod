package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Enchant items.", usage = "/<command> <list | addall | reset | add <name> | remove <name>>")
public class Command_enchant extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false;
        }

        ItemStack itemInHand = sender_p.getItemInHand();

        if (itemInHand == null)
        {
            playerMsg("You are holding an invalid item.");
            return true;
        }

        if (args[0].equalsIgnoreCase("list"))
        {
            boolean has_enchantments = false;

            StringBuilder possible_ench = new StringBuilder("Possible enchantments for held item: ");
            for (Enchantment ench : Enchantment.values())
            {
                if (ench.canEnchantItem(itemInHand))
                {
                    has_enchantments = true;
                    possible_ench.append(ench.getName()).append(", ");
                }
            }

            if (has_enchantments)
            {
                playerMsg(possible_ench.toString());
            }
            else
            {
                playerMsg("The held item has no enchantments.");
            }
        }
        else if (args[0].equalsIgnoreCase("addall"))
        {
            for (Enchantment ench : Enchantment.values())
            {
                /*
                 2013-07-20 23:17:13 [INFO] [TotalFreedomMod]: Command Error: enchant
                 java.lang.NullPointerException
                 at net.minecraft.server.v1_6_R2.EnchantmentDurability.canEnchant(SourceFile:33)
                 at org.bukkit.craftbukkit.v1_6_R2.enchantments.CraftEnchantment.canEnchantItem(CraftEnchantment.java:55)
                 at me.StevenLawson.TotalFreedomMod.Commands.Command_enchant.run(Command_enchant.java:56)
                 */
                try
                {
                    if (ench.canEnchantItem(itemInHand))
                    {
                        itemInHand.addEnchantment(ench, ench.getMaxLevel());
                    }
                }
                catch (Exception ex)
                {
                    TFM_Log.info("Error using " + ench.getName() + " on " + itemInHand.getType().name() + " held by " + sender_p.getName() + ".");
                }
            }

            playerMsg("Added all possible enchantments for this item.");
        }
        else if (args[0].equalsIgnoreCase("reset"))
        {
            for (Enchantment ench : itemInHand.getEnchantments().keySet())
            {
                itemInHand.removeEnchantment(ench);
            }

            playerMsg("Removed all enchantments.");
        }
        else
        {
            if (args.length < 2)
            {
                return false;
            }

            Enchantment ench = null;

            try
            {
                ench = Enchantment.getByName(args[1]);
            }
            catch (Exception ex)
            {
            }

            if (ench == null)
            {
                playerMsg(args[1] + " is an invalid enchantment for the held item. Type \"/enchant list\" for valid enchantments for this item.");
                return true;
            }

            if (args[0].equalsIgnoreCase("add"))
            {
                if (ench.canEnchantItem(itemInHand))
                {
                    itemInHand.addEnchantment(ench, ench.getMaxLevel());

                    playerMsg("Added enchantment: " + ench.getName());
                }
                else
                {
                    playerMsg("Can't use this enchantment on held item.");
                }
            }
            else if (TFM_Util.isRemoveCommand(args[0]))
            {
                itemInHand.removeEnchantment(ench);

                playerMsg("Removed enchantment: " + ench.getName());
            }
        }

        return true;
    }
}
