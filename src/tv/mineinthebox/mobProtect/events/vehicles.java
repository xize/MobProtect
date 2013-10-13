package tv.mineinthebox.mobProtect.events;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.mobProtect.util;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class vehicles implements Listener {

	@EventHandler
	public void checkOnVehicle(PlayerMoveEvent e) {
		if(e.getPlayer().isInsideVehicle()) {
			if(e.getPlayer().getVehicle() instanceof LivingEntity) {
				for(ProtectedRegion region : util.wg().getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation())) {
					if(!util.wg().canBuild(e.getPlayer(), e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF))) {
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
						e.getPlayer().getVehicle().eject();
						e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to sit on this mob");
					}
				}
			}
		}
	}

}
