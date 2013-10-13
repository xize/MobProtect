package tv.mineinthebox.mobProtect.minicommands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tv.mineinthebox.mobProtect.mobProtect;
import tv.mineinthebox.mobProtect.util;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class addToWhitelist {
	
	public static void addWhitelistedRegion(CommandSender sender, String[] args) {
		if(sender.hasPermission("mobprotect.admin")) {
			if(sender instanceof Player) {
				if(args[1].equalsIgnoreCase("add")) {
					Player p = (Player) sender;
					if(util.wg().getRegionManager(p.getWorld()).hasRegion(args[2])) {
						ProtectedRegion region = util.wg().getRegionManager(p.getWorld()).getRegionExact(args[2]);
						try {
							File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist" + File.separator + region.getId() + ".yml");
							if(!f.exists()) {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								con.set("regionOwner", region.getOwners().toPlayersString());
								con.save(f);
								sender.sendMessage(ChatColor.GREEN + "[mobProtect] " + ChatColor.GRAY + "region successfully whitelisted!");
							} else {
								sender.sendMessage(ChatColor.RED + "your region is allready whitelisted!");
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else if(sender.hasPermission("mobprotect.regionOwner") && !sender.hasPermission("mobprotect.admin")) {
			if(sender instanceof Player) {
				if(args[1].equalsIgnoreCase("add")) {
					Player p = (Player) sender;
					if(util.wg().getRegionManager(p.getWorld()).hasRegion(args[2])) {
						ProtectedRegion region = util.wg().getRegionManager(p.getWorld()).getRegionExact(args[2]);
						if(region.getOwners().contains(sender.getName())) {
							try {
								File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist" + File.separator + region.getId() + ".yml");
								if(!f.exists()) {
									FileConfiguration con = YamlConfiguration.loadConfiguration(f);
									con.set("regionOwner", region.getOwners().toPlayersString());
									con.save(f);
									sender.sendMessage(ChatColor.GREEN + "[mobProtect] " + ChatColor.GRAY + "region successfully whitelisted!");
								} else {
									sender.sendMessage(ChatColor.RED + "your region is allready whitelisted!");
								}
							} catch(Exception e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(ChatColor.RED + "you aren't owner of this region, maybe check a other world?");
						}
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you don't have permission to perfom this command!");
		}
	}

}
