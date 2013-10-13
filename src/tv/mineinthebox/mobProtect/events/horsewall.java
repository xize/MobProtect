package tv.mineinthebox.mobProtect.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import tv.mineinthebox.mobProtect.util;
import tv.mineinthebox.mobProtect.configuration.config;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class horsewall implements Listener {
	
	@EventHandler
	public void a(PlayerMoveEvent e) {
		if(e.getPlayer().isInsideVehicle()) {
			if(e.getPlayer().getVehicle().getType() == EntityType.HORSE) {
					if(e.getPlayer().getVehicle().getPassenger() instanceof Player) {
						Player p = (Player) e.getPlayer();
						WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
						for(ProtectedRegion region : wg.getRegionManager(p.getVehicle().getWorld()).getApplicableRegions(p.getVehicle().getLocation())) {
							if(!util.wg().canBuild(p, p.getVehicle().getLocation().getBlock())) {
								if(util.isWhitelisted(region.getId())) {
									return;
								}
								if(util.bypassedPlayers.contains(e.getPlayer().getName())) {
									return;
								}
								if(util.MobProtectDisabled(e.getPlayer().getWorld())) {
									return;
								}
								if(util.getAllowedRegionPlayers(region.getId()).contains(e.getPlayer().getName())) {
									return;
								}
								Vector direction = e.getFrom().toVector().subtract(e.getTo().toVector());
								direction.setX(8);
								direction.setZ(8); 
								p.getVehicle().setVelocity(direction.multiply(12).normalize());
								p.sendMessage(config.bounceOffHorsesMessage);
							}
						}
					} else {
						return;
					}
				}
			}
		}
}
