package tv.mineinthebox.mobProtect;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Monster;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import tv.mineinthebox.mobProtect.configuration.config;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class util {
	
	public static HashSet<String> bypassedPlayers = new HashSet<String>();
	
	public static boolean checkForWorldGuard() {
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static WorldGuardPlugin wg() {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			return (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		} else {
			return null;
		}
	}
	
	public static boolean isWhitelisted(String region) {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "whitelist" + File.separator + region +".yml");
			if(f.exists()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean MobProtectDisabled(World w) {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("disable-protection-perWorld." + w.getName())) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean checkMonster(Entity monster) {
		if(config.disableProtectionForMonsters) {
			if(monster instanceof Monster) {
				return true;
			} else if(monster instanceof Animals) {
				if(monster instanceof PigZombie) {
					return true;
				} else if(monster instanceof Slime) {
					return true;
				} else if(monster instanceof MagmaCube) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkVehicle() {
		try{
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("disableVehicleUse")) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean horseWall() {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("bounceOffHorsesFromSpawn.enable")) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean isEntityGriefEnabled() {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("disableEntityGrief")) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean isRegionOwner(Player p, String regionName) {
		if(wg().getRegionManager(p.getWorld()).hasRegion(regionName)) {
			ProtectedRegion region = wg().getRegionManager(p.getWorld()).getRegionExact(regionName);
			if(region.getOwners().contains(p.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<String> getAllowedRegionPlayers(String regionName) {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "allowedPlayers" + File.separator + regionName + ".yml");
			if(f.exists()) {
				ArrayList<String> list = new ArrayList<String>();
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				for(String name : con.getStringList("allowedPlayers")) {
					list.add(name);
				}
				return list;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		return list;
	}

}
