package tv.mineinthebox.mobProtect;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.mobProtect.minicommands.addToWhitelist;
import tv.mineinthebox.mobProtect.minicommands.allowPlayer;
import tv.mineinthebox.mobProtect.minicommands.bypass;
import tv.mineinthebox.mobProtect.minicommands.denyPlayer;
import tv.mineinthebox.mobProtect.minicommands.help;
import tv.mineinthebox.mobProtect.minicommands.reload;
import tv.mineinthebox.mobProtect.minicommands.removeFromWhitelist;
import tv.mineinthebox.mobProtect.minicommands.whitelist;

public class command implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("mobProtect.command")) {
			if(args.length == 0) {
				help.getHelp(sender);
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					help.getHelp(sender);
				} else if(args[0].equalsIgnoreCase("reload")) {
					reload.getReload(sender);
				} else if(args[0].equalsIgnoreCase("whitelist")) {
					whitelist.getWhitelist(sender); 
				} else if(args[0].equalsIgnoreCase("bypass")) {
					bypass.getbypass(sender);
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("whitelist")) {
					whitelist.getWhitelist(sender, args);
				} else if(args[0].equalsIgnoreCase("allow")) {
					allowPlayer.getAllowPlayer(sender, args);
				} else if(args[0].equalsIgnoreCase("deny")) {
					denyPlayer.setDenyPlayer(sender, args);
				}
			} else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("whitelist") && args[1].equalsIgnoreCase("add")) {
					addToWhitelist.addWhitelistedRegion(sender, args);
				} else if(args[0].equalsIgnoreCase("whitelist") && args[1].equalsIgnoreCase("remove")) {
					removeFromWhitelist.getRemoveFromWhitelist(sender, args);
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you have no permission to perform this command:\n"+ChatColor.GRAY + "permission: mobProtect.command");
		}
		return false;
	}

}
