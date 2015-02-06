package tv.mineinthebox.mobprotect.flags;

import com.sk89q.worldguard.protection.flags.StateFlag;

public class Flags {

	private final StateFlag MOB_PROTECT = new MobProtectFlag();
	private final StateFlag ALLOW_HORSE = new AllowHorseFlag();
	
	/**
	 * returns the mob protection flag
	 * 
	 * @author xize
	 * @return StateFlag
	 */
	public StateFlag getMobProtectFlag() {
		return MOB_PROTECT;
	}
	
	/**
	 * returns the horse allow flag
	 * 
	 * @author xize
	 * @return StateFlag
	 */
	public StateFlag getAllowHorseFlag() {
		return ALLOW_HORSE;
	}
	
	private class AllowHorseFlag extends StateFlag {
		public AllowHorseFlag() {
			super("allow-horse", true);
		}
		
	}
	
	private class MobProtectFlag extends StateFlag {
		public MobProtectFlag() {
			super("mob-protect", true);
		}
		
	}

}
