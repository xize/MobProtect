package tv.mineinthebox.mobprotect.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tv.mineinthebox.mobprotect.MobProtect;

public class ProtectPlayerAttackEvent implements Listener {

	private final MobProtect pl;

	public ProtectPlayerAttackEvent(MobProtect pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Animals || e.getEntity() instanceof Villager) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player)e.getDamager();
				if(pl.getManager().getWorldGuardManager().isInRegion(e.getEntity().getLocation())) {
					if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getMobProtectFlag(), e.getEntity().getLocation())) {
						if(!pl.getManager().getWorldGuardManager().isMember(p, e.getEntity().getLocation())) {
							p.sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you are not allowed to damage passive mobs in this region!");
							e.setCancelled(true);
						} else {
							if(e.isCancelled()) {
								e.setCancelled(false);
							}
						}
					}
				}
			} else if(e.getDamager() instanceof Projectile) {
				Projectile proj = (Projectile) e.getDamager();
				if(proj.getShooter() instanceof Player) {
					Player p = (Player)proj.getShooter();
					if(pl.getManager().getWorldGuardManager().isInRegion(e.getEntity().getLocation())) {
						if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getMobProtectFlag(), e.getEntity().getLocation())) {
							if(!pl.getManager().getWorldGuardManager().isMember(p, e.getEntity().getLocation())) {
								p.sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you are not allowed to damage passive mobs in this region!");
								e.setCancelled(true);
								proj.remove();
							} else {
								if(e.isCancelled()) {
									e.setCancelled(false);
								}
							}
						}
					}
				}
			} else if(e.getDamager() instanceof ThrownPotion) {
				ThrownPotion pot = (ThrownPotion) e.getDamager();
				if(pot.getShooter() instanceof Player) {
					Player p = (Player)pot.getShooter();
					if(pl.getManager().getWorldGuardManager().isInRegion(e.getEntity().getLocation())) {
						if(!pl.getManager().getWorldGuardManager().isFlagAllowed(pl.getFlags().getMobProtectFlag(), e.getEntity().getLocation())) {
							if(!pl.getManager().getWorldGuardManager().isMember(p, e.getEntity().getLocation())) {
								p.sendMessage(ChatColor.GREEN + "[MobProtect]: " + ChatColor.GRAY + "you are not allowed to damage passive mobs in this region!");
								e.setCancelled(true);
							} else {
								if(e.isCancelled()) {
									e.setCancelled(false);
								}
							}
						}
					}
				}
			}
		}
	}

}
