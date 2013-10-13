package tv.mineinthebox.mobProtect.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

import tv.mineinthebox.mobProtect.util;
import tv.mineinthebox.mobProtect.configuration.config;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class leashes implements Listener {

	@EventHandler
	public void unleash(PlayerUnleashEntityEvent e) {
		if(e.getEntity() instanceof LivingEntity) {
			if(!util.wg().canBuild(e.getPlayer(), e.getEntity().getLocation().getBlock())) {
				for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
					if(util.isWhitelisted(region.getId())) {
						return;
					}
					if(util.bypassedPlayers.contains(e.getPlayer().getName())) {
						return;
					}
					if(util.MobProtectDisabled(e.getEntity().getWorld())) {
						return;
					}
					if(util.getAllowedRegionPlayers(region.getId()).contains(e.getPlayer().getName())) {
						return;
					}
				}
				e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to unleash this mob " + e.getEntity().getType().name().toLowerCase());
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void hangingBreak(HangingBreakByEntityEvent e) {
		if(e.getEntity().getType() == EntityType.LEASH_HITCH) {
			if(e.getRemover() instanceof Player) {
				Player p = (Player) e.getRemover();
				if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
					for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
						if(util.isWhitelisted(region.getId())) {
							return;
						}
						if(util.bypassedPlayers.contains(p.getName())) {
							return;
						}
						if(util.MobProtectDisabled(e.getEntity().getWorld())) {
							return;
						}
						if(util.getAllowedRegionPlayers(region.getId()).contains(p.getName())) {
							return;
						}
					}
					p.sendMessage(ChatColor.RED + "you are not allowed to unleash this mob " + e.getEntity().getType().name().toLowerCase());
					e.setCancelled(true);
				}
			} else if(e.getRemover() instanceof TNTPrimed) {
				if(config.disableEntityGrief) {
					TNTPrimed tnt = (TNTPrimed) e.getRemover();
					if(tnt.getSource() instanceof Player) {
						Player p = (Player) tnt.getSource();
						if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
							for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
								if(util.isWhitelisted(region.getId())) {
									return;
								}
								if(util.bypassedPlayers.contains(p.getName())) {
									return;
								}
								if(util.MobProtectDisabled(e.getEntity().getWorld())) {
									return;
								}
								if(util.getAllowedRegionPlayers(region.getId()).contains(p.getName())) {
									return;
								}
							}
							p.sendMessage(ChatColor.RED + "you are not allowed to unleash this mob " + e.getEntity().getType().name().toLowerCase());
							e.setCancelled(true);
						}
					} else if(tnt.getSource() instanceof Creeper) {
						for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
							if(util.isWhitelisted(region.getId())) {
								return;
							}
							if(util.MobProtectDisabled(e.getEntity().getWorld())) {
								return;
							}
						}
						e.setCancelled(true);
					} else if(tnt.getSource() instanceof Wither || tnt.getSource() instanceof WitherSkull) {
						for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
							if(util.isWhitelisted(region.getId())) {
								return;
							}
							if(util.MobProtectDisabled(e.getEntity().getWorld())) {
								return;
							}
						}
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void leash(PlayerLeashEntityEvent e) {
		if(e.getEntity() instanceof LivingEntity) {
			if(!util.wg().canBuild(e.getPlayer(), e.getEntity().getLocation().getBlock())) {
				for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
					if(util.isWhitelisted(region.getId())) {
						return;
					}
					if(util.bypassedPlayers.contains(e.getPlayer().getName())) {
						return;
					}
					if(util.MobProtectDisabled(e.getEntity().getWorld())) {
						return;
					}
					if(util.getAllowedRegionPlayers(region.getId()).contains(e.getPlayer().getName())) {
						return;
					}
				}
				e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to leash this mob " + e.getEntity().getType().name().toLowerCase());
				e.setCancelled(true);
			}
		}
	}
}
