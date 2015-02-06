package tv.mineinthebox.mobprotect.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import tv.mineinthebox.mobprotect.MobProtect;

public class Handler {
	
	private final MobProtect pl;
	
	public Handler(MobProtect pl) {
		this.pl = pl;
	}
	
	public void start() {
		setListener(new ProtectLeashEvent(pl));
		setListener(new ProtectPlayerAttackEvent(pl));
		setListener(new ProtectEntityEvent(pl));
		setListener(new ProtectHorseRegionEvent(pl));
	}
	
	public void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, pl);
	} 

}
