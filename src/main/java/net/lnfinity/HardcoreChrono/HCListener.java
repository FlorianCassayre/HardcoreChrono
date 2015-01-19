package net.lnfinity.HardcoreChrono;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HCListener implements Listener {

	private HardcoreChrono p;

	public HCListener(HardcoreChrono plugin) {
		p = plugin;
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity().getHealth() == 0) {
			if (e.getEntity() instanceof EnderDragon) {
				p.setHasKilledDragon(true);

			} else if (e.getEntity() instanceof Wither) {
				p.setHasKilledWither(true);

			} else if (e.getEntity() instanceof Guardian) {
				Guardian g = (Guardian) e.getEntity();
				if (g.isElder()) {
					p.setHasKilledGuardian(true);

				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		p.getScoreboard().assignScoreboard(e.getPlayer());
	}
}
