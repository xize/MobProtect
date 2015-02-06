package tv.mineinthebox.mobprotect.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import tv.mineinthebox.mobprotect.MobProtect;

public class ProtectHorseRegionEvent implements Listener {

	private final MobProtect pl;

	public ProtectHorseRegionEvent(MobProtect pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onMov(PlayerMoveEvent e) {
			if(e.getPlayer().getVehicle() instanceof Horse) {
				Horse horse = (Horse) e.getPlayer().getVehicle();
				if(pl.getManager().getWorldGuardManager().isInRegion(e.getPlayer().getLocation())) {
					if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getAllowHorseFlag(), e.getPlayer().getLocation())) {
						if(!pl.getManager().getWorldGuardManager().isMember(e.getPlayer(), e.getPlayer().getLocation())) {
							e.getPlayer().sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you are not allowed to enter this region with a horse!");
							Vector vec = e.getFrom().getDirection();
							vec.setY(0);
							horse.setVelocity(vec.multiply(-1).normalize());
						}
					}
				}
			}
	}
	
	@EventHandler
	public void onMount(PlayerInteractEntityEvent e) {
		if(e.getRightClicked() instanceof Horse) {
			if(pl.getManager().getWorldGuardManager().isInRegion(e.getRightClicked().getLocation())) {
				if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getAllowHorseFlag(), e.getRightClicked().getLocation())) {
					if(!pl.getManager().getWorldGuardManager().isMember(e.getPlayer(), e.getRightClicked().getLocation())) {
						e.getPlayer().sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you cannot mount on a horse!");
						e.setCancelled(true);
					}
				}
			}
		}
	}

}
