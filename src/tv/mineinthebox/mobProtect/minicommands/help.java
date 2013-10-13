package tv.mineinthebox.mobProtect.minicommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class help {

	public static void getHelp(CommandSender sender) {
		if(sender.hasPermission("mobProtect.admin")) {
			sender.sendMessage(ChatColor.GOLD + ".oO___[mobProtect help]___Oo.");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect help " + ChatColor.WHITE + ": shows help");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect reload " + ChatColor.WHITE + ": reload mobprotect");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect whitelist " + ChatColor.WHITE + ": shows the list of all whitelisted regions");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect whitelist <player> " + ChatColor.WHITE + ": get a list of all whitelisted regions which belongs to this player");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect whitelist add <region name> " + ChatColor.WHITE + ": adds a region to the whitelist");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect whitelist remove <region name> " + ChatColor.WHITE + ": remove a whitelisted region");
			sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/mobprotect bypass " + ChatColor.WHITE + ": bypass all mobprotection");
		} else if(sender.hasPermission("mobProtect.regionOwner") && !sender.hasPermission("mobProtect.admin")) {
			sender.sendMessage(ChatColor.GOLD + ".oO___[mobProtect help]___Oo.");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect help " + ChatColor.WHITE + ": shows help");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect whitelist " + ChatColor.WHITE + ": shows the list of all your owned whitelisted regions");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect whitelist add <region name> " + ChatColor.WHITE + ": adds a region to your owned region whitelist");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect whitelist remove <region name> " + ChatColor.WHITE + ": remove a whitelisted region");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect allow <player> " + ChatColor.WHITE + ": allows a player to bypass mobProtect when the commandsender is the region owner");
			sender.sendMessage(ChatColor.DARK_GRAY + "RegionOwner: " + ChatColor.GRAY + "/mobprotect deny <player> " + ChatColor.WHITE + ": deny a player to bypass mobProtect when the commandsender is the region owner");
		} else {
			sender.sendMessage(ChatColor.GOLD + ".oO___[mobProtect help]___Oo.");
			sender.sendMessage(ChatColor.RED + "you don't have permission to use mobProtect");
		}
	}
}
