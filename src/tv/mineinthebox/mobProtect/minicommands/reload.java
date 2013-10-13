package tv.mineinthebox.mobProtect.minicommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.mobProtect.events.handler;

public class reload {
	
	public static void getReload(CommandSender sender) {
		if(sender.hasPermission("mobProtect.admin")) {
			handler.reload();
			sender.sendMessage(ChatColor.GREEN + "[MobProtect] " + ChatColor.GRAY + " has been reloaded successfully");
		} else {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command\n"+ChatColor.GRAY + "permission: mobProtect.admin");
		}
	}

}
