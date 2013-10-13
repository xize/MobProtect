package tv.mineinthebox.mobProtect.configuration;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tv.mineinthebox.mobProtect.logType;
import tv.mineinthebox.mobProtect.mobProtect;

public class config {
	
	public static boolean bounceOffHorses = false;
	public static String bounceOffHorsesMessage = "";
	public static boolean leadProtection = false;
	public static boolean disableVehiclesUse = false;
	public static boolean disableEntityGrief = false;
	public static boolean disableProtectionForMonsters = false;
	
	public static void setConfigurationInMemory() {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				bounceOffHorses = con.getBoolean("bounceOffHorsesFromSpawn.enable");
				bounceOffHorsesMessage = con.getString("bounceOffHorsesFromSpawn.message");
				leadProtection = con.getBoolean("leads.protection");
				disableVehiclesUse = con.getBoolean("disableVehicleUse");
				disableEntityGrief = con.getBoolean("disableEntityGrief");
				disableProtectionForMonsters = con.getBoolean("disableprotection.formonsters");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createConfig() {
		try {
			File f = new File(mobProtect.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				mobProtect.setLogMessage("[mobProtect] config.yml found", logType.info);
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(!con.isSet("disableprotection.formonsters")) {
					mobProtect.setLogMessage("[mobProtect] the config.yml whas outdated so we updated it", logType.info);
					con.set("disableprotection.formonsters", true);
					con.save(f);
				}
				for(World w : Bukkit.getWorlds()) {
					if(!con.isSet("disable-protection-perWorld." + w.getName())) {
						mobProtect.setLogMessage("[mobProtect] config.yml has been updated with new worlds!", logType.info);
						con.set("disable-protection-perWorld." + w.getName(), false);
						con.save(f);
					}
				}
				if(!con.isSet("disableEntityGrief")) {
					mobProtect.setLogMessage("[mobProtect] the config.yml whas outdated so we updated it", logType.info);
					con.set("disableEntityGrief", false);
					con.save(f);
				}
				if(!con.isSet("disableVehicleUse")) {
					con.set("disableVehicleUse", false);
					con.save(f);
				}
				setConfigurationInMemory();
			} else {
				mobProtect.setLogMessage("[mobProtect] config.yml not found, generating one now", logType.info);
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set("bounceOffHorsesFromSpawn.enable", true);
				con.set("bounceOffHorsesFromSpawn.message", "this is the malasia wall you are not allowed to enter with a horse! >:D");
				con.set("leads.protection", true);
				con.set("disableprotection.formonsters", true);
				con.set("disableEntityGrief", false);
				con.set("disableVehicleUse", false);
				for(World w : Bukkit.getWorlds()) {
					con.set("disable-protection-perWorld." + w.getName(), false);
				}
				con.save(f);
				setConfigurationInMemory();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
