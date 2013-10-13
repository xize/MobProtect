package tv.mineinthebox.mobProtect.events;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import tv.mineinthebox.mobProtect.mobProtect;
import tv.mineinthebox.mobProtect.configuration.config;

public class handler {
	
	public static void launch() {
		if(config.leadProtection){setListener(new leashes());}
		setListener(new unknownEntityDamage());
		setListener(new entityAttack());
		if(config.disableVehiclesUse){setListener(new vehicles());}
		if(config.bounceOffHorses){setListener(new horsewall());}
	}
	
	public static void reload() {
		HandlerList.unregisterAll(mobProtect.getPlugin());
		config.setConfigurationInMemory();
		launch();
	}
	
	public static void setListener(Listener listener) { 
		Bukkit.getPluginManager().registerEvents(listener, mobProtect.getPlugin());
	}

}
