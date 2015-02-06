package tv.mineinthebox.mobprotect;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tv.mineinthebox.mobprotect.enums.LogType;
import tv.mineinthebox.mobprotect.flags.Flags;
import tv.mineinthebox.mobprotect.listeners.Handler;
import tv.mineinthebox.mobprotect.managers.Manager;

public class MobProtect extends JavaPlugin {

	private Flags flags;
	private Manager manager;
	private Handler handler;

	public void onEnable() {
		this.flags = new Flags();
		this.manager = new Manager(this);
		this.handler = new Handler(this);
		try {
			manager.getWorldGuardManager().registerFlag(getFlags().getAllowHorseFlag());
			manager.getWorldGuardManager().registerFlag(getFlags().getMobProtectFlag());
		} catch(Exception e) {
			e.printStackTrace();
		}
		handler.start();
		log("has been enabled!", LogType.INFO);
	}

	public void onDisable() {
		try {
			manager.getWorldGuardManager().unregisterFlag(getFlags().getAllowHorseFlag());
			manager.getWorldGuardManager().unregisterFlag(getFlags().getMobProtectFlag());
		} catch(Exception e) {
			e.printStackTrace();
		}
		log("has been disabled!", LogType.INFO);
	}

	/**
	 * sent a message towards the console sender
	 * 
	 * @author xize
	 * @param msg - the message being sent
	 * @param type - the type of message
	 */
	public void log(String msg, LogType type) {
		Bukkit.getConsoleSender().sendMessage(type.getPrefix() + msg);
	}
	
	/**
	 * returns the manager
	 * 
	 * @author xize
	 * @return Manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * returns the flags for worldguard
	 * 
	 * @author xize
	 * @return Flags
	 */
	public Flags getFlags() {
		return flags;
	}

}
