package tv.mineinthebox.mobprotect.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;

import tv.mineinthebox.mobprotect.MobProtect;

public class ProtectLeashEvent implements Listener {

	private final MobProtect pl;

	public ProtectLeashEvent(MobProtect pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onLeash(PlayerLeashEntityEvent e) {
		if(e.getEntity() instanceof Animals) {
			if(pl.getManager().getWorldGuardManager().isInRegion(e.getEntity().getLocation())) {
				if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getMobProtectFlag(), e.getEntity().getLocation())) {
					if(!pl.getManager().getWorldGuardManager().isMember(e.getPlayer(), e.getEntity().getLocation())) {
						e.getPlayer().sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you are not allowed to leash this mob in this region!");
						e.setCancelled(true);
					}
				}
			}
		}
	}

}
