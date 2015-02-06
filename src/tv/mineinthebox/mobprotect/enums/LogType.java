package tv.mineinthebox.mobprotect.enums;

import org.bukkit.ChatColor;

public enum LogType {
	
	INFO(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY),
	SEVERE(ChatColor.RED + "[MobProtect]: " + ChatColor.GRAY);
	
	private final String prefix;
	
	private LogType(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * returns the prefix of the log message
	 * 
	 * @author xize
	 * @return String
	 */
	public String getPrefix() {
		return prefix;
	}

}
