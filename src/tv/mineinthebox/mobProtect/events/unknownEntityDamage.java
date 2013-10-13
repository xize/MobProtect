package tv.mineinthebox.mobProtect.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import tv.mineinthebox.mobProtect.util;
import tv.mineinthebox.mobProtect.configuration.config;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class unknownEntityDamage implements Listener {

	@EventHandler
	public void checkOnUnknownEntityDamage(EntityDamageEvent e) {
		if(config.disableEntityGrief){
			if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player)) {
				if(config.disableProtectionForMonsters) {
					if(util.checkMonster(e.getEntity())) {
						return;
					}
				}
				for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
					if(util.isWhitelisted(region.getId())) {
						return;
					}
					if(util.MobProtectDisabled(e.getEntity().getWorld())) {
						return;
					}
					for(DamageCause cause : DamageCause.values()) {
						if(cause == DamageCause.MAGIC || cause == DamageCause.POISON || cause == DamageCause.PROJECTILE || cause == DamageCause.ENTITY_ATTACK) {
							
						} else {
							if(e.getCause() == cause) {
								e.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}

}
