package tv.mineinthebox.mobprotect.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import tv.mineinthebox.mobprotect.MobProtect;

public class ProtectEntityEvent implements Listener {

	private final MobProtect pl;

	public ProtectEntityEvent(MobProtect pl) {
		this.pl = pl;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onDamage(EntityDamageEvent e) {

		if(e.isCancelled()) {
			return;
		}

		if(e.getEntity() instanceof Animals || e.getEntity() instanceof Villager) {
			if(pl.getManager().getWorldGuardManager().isInRegion(e.getEntity().getLocation())) {
				if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getMobProtectFlag(), e.getEntity().getLocation())) {
					if(e.getCause() == DamageCause.BLOCK_EXPLOSION) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.CONTACT) {
						//TODO: check if a region owner will be affected by this.
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.DROWNING) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.ENTITY_ATTACK) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.ENTITY_EXPLOSION) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.FALL) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.FALLING_BLOCK) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.FIRE) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.FIRE_TICK) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.LAVA) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.LIGHTNING) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.MAGIC) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.MELTING) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.POISON) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.PROJECTILE) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.SUFFOCATION) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.THORNS) {
						e.setCancelled(true);
					} else if(e.getCause() == DamageCause.WITHER) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

}
