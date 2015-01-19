package net.lnfinity.HardcoreChrono;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreChrono extends JavaPlugin {

	private boolean hasKilledDragon = false;
	private boolean hasKilledWither = false;
	private boolean hasKilledGuardian = false;
	
	private NumberFormat formatter = new DecimalFormat("00");

	private HCScoreboard s;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new HCListener(this), this);

		s = new HCScoreboard(this);
		
		getCommand("hardcorechrono").setExecutor(new HCCommands(this));

		this.getServer().setDefaultGameMode(GameMode.SPECTATOR);

		// We load the config, if it exists.
		FileConfiguration config = this.getConfig();
		hasKilledDragon = config.getBoolean("hasKilledDragon", false);
		hasKilledWither = config.getBoolean("hasKilledWither", false);
		hasKilledGuardian = config.getBoolean("hasKilledGuardian", false);
		
		// We need to assign the scoreboard to every lgged-in player (if the server is reloaded)
		s.assignScoreboard();
	}

	public HCScoreboard getScoreboard() {
		return s;
	}

	public boolean hasKilledDragon() {
		return hasKilledDragon;
	}

	public void setHasKilledDragon(boolean bool) {
		hasKilledDragon = bool;
		
		getConfig().set("hasKilledDragon", hasKilledDragon);
		saveConfig();
		
		killedBoss();
	}

	public boolean hasKilledWither() {
		return hasKilledWither;
	}

	public void setHasKilledWither(boolean bool) {
		hasKilledWither = bool;
		
		getConfig().set("hasKilledWither", hasKilledWither);
		saveConfig();
		
		killedBoss();
	}

	public boolean hasKilledGuardian() {
		return hasKilledGuardian;
	}

	public void setHasKilledGuardian(boolean bool) {
		hasKilledGuardian = bool;
		
		getConfig().set("hasKilledGuardian", hasKilledGuardian);
		saveConfig();
		
		killedBoss();
	}

	public void killedBoss() {
		if (hasKilledDragon() && hasKilledWither() && hasKilledGuardian()) {
			s.getTimer().stopTimer();
			s.updateDisplay();

			for (Player online : Bukkit.getOnlinePlayers()) {
				online.sendMessage(ChatColor.BOLD
						+ "---------------------------------------------");
				online.sendMessage("");
				online.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD
						+ "              Hardcore Chrono " + ChatColor.WHITE
						+ "" + ChatColor.BOLD + "complet !");
				online.sendMessage("");
				online.sendMessage(ChatColor.BOLD + "  Temps : "
						+ ChatColor.BOLD
						+ formatter.format(s.getTimer().getHours())
						+ ChatColor.GRAY + "" + ChatColor.BOLD + ":"
						+ ChatColor.WHITE + "" + ChatColor.BOLD
						+ formatter.format(s.getTimer().getMinutes())
						+ ChatColor.GRAY + "" + ChatColor.BOLD + ":"
						+ ChatColor.WHITE + "" + ChatColor.BOLD
						+ formatter.format(s.getTimer().getSeconds()));

				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage("");
				online.sendMessage(ChatColor.BOLD
						+ "---------------------------------------------");
			}
		}
	}
}
