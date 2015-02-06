package tv.mineinthebox.mobprotect.managers;

import tv.mineinthebox.mobprotect.MobProtect;

public class Manager {
	
	private final MobProtect pl;
	
	private WorldGuardManager worldguard;
	
	public Manager(MobProtect pl) {
		this.pl = pl;
	}
	
	/**
	 * returns the worldguard manager
	 * 
	 * @author xize
	 * @return WorldGuardManager
	 */
	public WorldGuardManager getWorldGuardManager() {
		if(!(worldguard instanceof WorldGuardManager)) {
			this.worldguard = new WorldGuardManager(pl);
		}
		return worldguard;
	}

}
