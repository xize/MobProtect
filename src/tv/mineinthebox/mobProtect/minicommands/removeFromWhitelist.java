package tv.mineinthebox.mobProtect.minicommands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import tv.mineinthebox.mobProtect.mobProtect;
import tv.mineinthebox.mobProtect.util;

public class removeFromWhitelist {

	public static void getRemoveFromWhitelist(CommandSender sender, String[] args) {
		if(sender.hasPermission("mobProtect.admin")) {
			try {
				File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist" + File.separator + args[2] + ".yml");
				if(f.exists()) {
					f.delete();
					sender.sendMessage(ChatColor.GREEN + "successfully removed whitelisted region " + args[2]);
				} else {
					sender.sendMessage(ChatColor.RED + "this region is not whitelisted!");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(sender.hasPermission("mobProtect.regionOwner") && !sender.hasPermission("mobProtect.admin")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				try {
					File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist" + File.separator + args[2] + ".yml");
					if(f.exists()) {
						if(util.wg().getRegionManager(p.getWorld()).hasRegion(args[2])) {
							ProtectedRegion region = util.wg().getRegionManager(p.getWorld()).getRegionExact(args[2]);
							if(region.getOwners().contains(p.getName())) {
								f.delete();
								sender.sendMessage(ChatColor.GREEN + "successfully removed whitelisted region " + args[2]);
							} else {
								sender.sendMessage(ChatColor.RED + "you are not allowed to remove this whitelist!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "this region does not exist in worldguard, but a whitelist file still exist and marked for deletion");
							f.delete();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "this region is not whitelisted!");
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				sender.sendMessage(ChatColor.RED + "a console is not allowed to perform this command");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you don't have permission to remove whitelisted regions!");
		}
	}
	
}
