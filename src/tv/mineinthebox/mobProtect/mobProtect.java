package tv.mineinthebox.mobProtect;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import tv.mineinthebox.mobProtect.configuration.config;
import tv.mineinthebox.mobProtect.events.handler;

public class mobProtect extends JavaPlugin {
	private static mobProtect plugin;
	private static Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		plugin = this;
		setLogMessage("has been enabled!", logType.info);
		config.createConfig();
		getCommand("mobprotect").setExecutor(new command());
		handler.launch();
	}
	
	public void onDisable() {
		setLogMessage("has been disabled!", logType.info);
	}
	
	public static void setLogMessage(String message, logType type) {
		if(type == logType.info) {
			log.info(getPlugin().getDescription().getName() + " " + getPlugin().getDescription().getVersion() + ": " + message);
		} else if(type == logType.severe) {
			log.severe(getPlugin().getDescription().getName() + " " + getPlugin().getDescription().getVersion() + ": " + message);
		}
	}
	
	public static util getApi() {
		util Util = new util();
		return Util;
	}
	
	public static mobProtect getPlugin() {
		if(plugin != null) {
			return plugin;	
		}
		return null;
	}
}
