package tv.mineinthebox.mobProtect.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import tv.mineinthebox.mobProtect.util;
import tv.mineinthebox.mobProtect.configuration.config;

public class entityAttack implements Listener {

	@EventHandler
	public void onPlayerStrikeEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player)) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
					for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
						if(util.checkMonster(e.getEntity())) {
							return;
						}
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
					p.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to attack this mob " + e.getEntity().getType().name().toLowerCase());
					e.setCancelled(true);
				}
			} else if(e.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) e.getDamager();
				if(arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();
					if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
						for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
							if(util.checkMonster(e.getEntity())) {
								return;
							}
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
						p.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to attack this mob " + e.getEntity().getType().name().toLowerCase());
						e.setCancelled(true);
					}
				}
			} else if(e.getDamager() instanceof ThrownPotion) {
				ThrownPotion pot = (ThrownPotion) e.getDamager();
				if(pot.getShooter() instanceof Player) {
					Player p = (Player) pot.getShooter();
					if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
						for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
							if(util.checkMonster(e.getEntity())) {
								return;
							}
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
						p.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to attack this mob " + e.getEntity().getType().name().toLowerCase());
						e.setCancelled(true);
					}
				}
			} else if(e.getDamager() instanceof TNTPrimed) {
				TNTPrimed tnt = (TNTPrimed) e.getDamager();
				if(tnt.getSource() instanceof Player) {
					Player p = (Player) tnt.getSource();
					if(!util.wg().canBuild(p, e.getEntity().getLocation().getBlock())) {
						for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
							if(util.checkMonster(e.getEntity())) {
								return;
							}
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
						p.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to attack this mob " + e.getEntity().getType().name().toLowerCase());
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onEntityStrikeEntity(EntityDamageByEntityEvent e) {
		if(config.disableEntityGrief) {
			if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player) && !(e.getEntity() instanceof Monster)) {
				if(e.getDamager() instanceof Wither) {
					for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
						if(util.isWhitelisted(region.getId())) {
							return;
						}
						if(util.MobProtectDisabled(e.getEntity().getWorld())) {
							return;
						}
					}
					e.setCancelled(true);
				} else if(e.getDamager() instanceof WitherSkull) {
					for(ProtectedRegion region : util.wg().getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())) {
						if(util.isWhitelisted(region.getId())) {
							return;
						}
						if(util.MobProtectDisabled(e.getEntity().getWorld())) {
							return;
						}
					}
					e.setCancelled(true);
				} else if(e.getDamager() instanceof TNTPrimed) {
					TNTPrimed primed = (TNTPrimed) e.getDamager();
					if(!(primed.getSource() instanceof Player)) {
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
				} else if(e.getDamager() instanceof ThrownPotion) {
					ThrownPotion pot = (ThrownPotion) e.getDamager();
					if(!(pot.getShooter() instanceof Player)) {
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
				} else if(e.getDamager() instanceof Arrow) {
					Arrow arrow = (Arrow) e.getDamager();
					if(!(arrow.getShooter() instanceof Player)) {
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
				} else if(e.getDamager() instanceof Fireball) {
					Fireball fireball = (Fireball) e.getDamager();
					if(!(fireball.getShooter() instanceof Player)) {
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

}
