package tv.mineinthebox.mobProtect.minicommands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tv.mineinthebox.mobProtect.mobProtect;
import tv.mineinthebox.mobProtect.util;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class allowPlayer {

	public static void getAllowPlayer(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			if(sender.hasPermission("mobProtect.regionOwner") && !sender.hasPermission("mobProtect.admin")) {
				Player p = (Player) sender;
				for(ProtectedRegion region : util.wg().getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
					if(region.isOwner(p.getName())) {
						try {
							File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "allowedPlayers" + File.separator + region.getId() + ".yml");
							if(f.exists()) {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								ArrayList<String> users = new ArrayList<String>();
								for(String array : con.getStringList("allowedPlayers")) {
									users.add(array);
								}
								if(users.contains(args[1])) {
									sender.sendMessage(ChatColor.RED + "this player is allready added");
								} else {
									users.add(args[1]);
									con.set("allowedPlayers", users);
									con.save(f);
									users.clear();
									sender.sendMessage(ChatColor.GREEN + "player successfully added as allowed in the region " + region.getId());	
								}
							} else {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								ArrayList<String> users = new ArrayList<String>();
								users.add(args[1]);
								con.set("allowedPlayers", users);
								con.save(f);
								users.clear();
								sender.sendMessage(ChatColor.GREEN + "player successfully added as allowed in the region " + region.getId());
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "you are not the region owner!");
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "the console cannot perform this command as it can't find the entity's location");
		}
	}
	
}
