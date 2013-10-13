package tv.mineinthebox.mobProtect.minicommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.mobProtect.util;

public class bypass {
	
	public static void getbypass(CommandSender sender) {
		if(sender.hasPermission("mobProtect.admin")) {
			if(util.bypassedPlayers.contains(sender.getName())) {
				util.bypassedPlayers.remove(sender.getName());
				sender.sendMessage(ChatColor.GREEN + "[mobProtect]" + ChatColor.GRAY + " bypass disabled");
			} else {
				util.bypassedPlayers.add(sender.getName());
				sender.sendMessage(ChatColor.GREEN + "[mobProtect]" + ChatColor.GRAY + " bypass enabled");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you don't have permissions for this command\n"+ChatColor.GRAY + "permission: mobProtect.admin");
		}
	}

}
