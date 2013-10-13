package tv.mineinthebox.mobProtect.minicommands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import tv.mineinthebox.mobProtect.mobProtect;
import tv.mineinthebox.mobProtect.util;

public class whitelist {

	public static void getWhitelist(CommandSender sender) {
		if(sender.hasPermission("mobProtect.admin")) {
			sender.sendMessage(ChatColor.GOLD + ".oO___[whitelist regions]___Oo.");
			try {
				File dir = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist");
				File[] list = dir.listFiles();
				if(dir.isDirectory()) {
					for(int i = 0; i < list.length; i++) {
						File f = list[i];
						sender.sendMessage(ChatColor.GRAY + "" + i + ":" + " " + ChatColor.GREEN + f.getName().replace(".yml", ""));
					}
				} else {
					sender.sendMessage(ChatColor.RED + "no whitelisted regions found");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(sender.hasPermission("mobProtect.regionOwner") && !sender.hasPermission("mobProtect.admin")) {
			if(sender instanceof Player) {
				sender.sendMessage(ChatColor.GOLD + ".oO___[whitelist regions]___Oo.");
				Player p = (Player) sender;
				try {
					File dir = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist");
					File[] list = dir.listFiles();
					if(dir.isDirectory()) {
						for(int i = 0; i < list.length; i++) {
							File f = list[i];
							if(util.wg().getRegionManager(p.getWorld()).hasRegion(f.getName().replace(".yml", ""))) {
								ProtectedRegion region = util.wg().getRegionManager(p.getWorld()).getRegion(f.getName().replace(".yml", ""));
								if(region.isOwner(p.getName())) {
									sender.sendMessage(ChatColor.GRAY + "" + i + " " + ChatColor.GREEN + region.getId());
								}
							}
						}
					} else {
						sender.sendMessage(ChatColor.RED + "no whitelisted regions found");
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				sender.sendMessage(ChatColor.RED + "a console can't run a region owner command");
			}
		}

	}

	public static void getWhitelist(CommandSender sender, String[] args) {
		if(args.length == 2) {
			if(sender.hasPermission("mobProtect.admin")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					File dir = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist");
					if(dir.isDirectory()) {
						File[] list = dir.listFiles();
						for(int i = 0; i < list.length;i++) {
							File f = list[i];
							if(util.wg().getRegionManager(p.getWorld()).hasRegion(f.getName().replace(".yml", ""))) {
								ProtectedRegion region = util.wg().getRegionManager(p.getWorld()).getRegionExact(f.getName().replace(".yml", ""));
								if(region.getOwners().getPlayers().contains(args[1].toLowerCase())) {
									sender.sendMessage(ChatColor.GRAY + "" + i + ChatColor.GREEN + ": " + region.getId());
									return;
								} else if(i == (list.length - 1)) {
									if(!region.getOwners().getPlayers().contains(args[1].toLowerCase())) {
										sender.sendMessage(ChatColor.RED + "it seems this user does not own whitelisted regions");
										return;
									}
								}
							}
						}
					} else {
						sender.sendMessage(ChatColor.RED + "no whitelist regions found!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "a console can't run a region owner command");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "you don't have permission to perform this command:\n"+ChatColor.GRAY+"permission: mobprotect.admin");
			}
		}
	}

}
