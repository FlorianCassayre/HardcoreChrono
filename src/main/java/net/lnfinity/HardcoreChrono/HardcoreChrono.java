package net.lnfinity.HardcoreChrono;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreChrono extends JavaPlugin {

	private boolean hasKilledDragon = false;
	private boolean hasKilledWither = false;
	private boolean hasKilledGuardian = false;

	private HCScoreboard s;

	public HCScoreboard getScoreboard() {
		return s;
	}

	public boolean hasKilledDragon() {
		return hasKilledDragon;
	}

	public void setHasKilledDragon(boolean bool) {
		hasKilledDragon = bool;
		killedBoss();
	}

	public boolean hasKilledWither() {
		return hasKilledWither;
	}

	public void setHasKilledWither(boolean bool) {
		hasKilledWither = bool;
		killedBoss();
	}

	public boolean hasKilledGuardian() {
		return hasKilledGuardian;
	}

	public void setHasKilledGuardian(boolean bool) {
		hasKilledGuardian = bool;
		killedBoss();
	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new HCListener(this),
				this);

		s = new HCScoreboard(this);
		s.getTimer().startTimer();

		this.getServer().setDefaultGameMode(GameMode.SPECTATOR);

	}

	public String getTimeString(int val) {
		String s = String.valueOf(val);
		if (s.length() == 1) {
			return "0" + s;
		}
		return s;
	}

	public void killedBoss() {
		if (hasKilledDragon() && hasKilledWither() && hasKilledGuardian()) {
			s.getTimer().stopTimer();

			for (Player online : Bukkit.getOnlinePlayers()) {
				online.sendMessage(ChatColor.BOLD
						+ "---------------------------------------------");
				online.sendMessage("");
				online.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD
						+ "              Hardcore Chrono " + ChatColor.WHITE
						+ "" + ChatColor.BOLD + "terminé !");
				online.sendMessage("");
				online.sendMessage(ChatColor.BOLD + "  Temps : "
						+ ChatColor.BOLD
						+ getTimeString(s.getTimer().getHours())
						+ ChatColor.GRAY + "" + ChatColor.BOLD + ":"
						+ ChatColor.WHITE + "" + ChatColor.BOLD
						+ getTimeString(s.getTimer().getMinutes())
						+ ChatColor.GRAY + "" + ChatColor.BOLD + ":" + ChatColor.WHITE + "" + ChatColor.BOLD
						+ getTimeString(s.getTimer().getSeconds()));

				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage(ChatColor.BOLD + "---------------------------------------------");
			}
		}
	}
}