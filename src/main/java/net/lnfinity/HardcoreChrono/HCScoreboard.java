package net.lnfinity.HardcoreChrono;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class HCScoreboard {

	private HardcoreChrono p = null;
	private HCTimer t = null;
	private Scoreboard board = null;
	private Objective objective = null;
	
	private NumberFormat formatter = new DecimalFormat("00");
	
	public HCScoreboard(HardcoreChrono plugin) {
		p = plugin;
		t = new HCTimer(p);
		
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		updateDisplay();
	}

	public HCTimer getTimer() {
		return t;
	}

	public void updateDisplay() {
		
		if(objective != null) {
			objective.unregister();
		}
		
		objective = board.registerNewObjective("hardcorechrono", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Hardcore Chrono");

		objective.getScore("").setScore(6);

		objective.getScore(formatter.format(t.getHours())
				+ ChatColor.GRAY + ":" + ChatColor.WHITE
				+ formatter.format(t.getMinutes()) + ChatColor.GRAY + ":"
				+ ChatColor.WHITE + formatter.format(t.getSeconds())).setScore(5);
		
		objective.getScore(" ").setScore(4);

		if (p.hasKilledDragon()) {
			objective.getScore(ChatColor.GREEN + "Dragon").setScore(3);
		} else {
			objective.getScore("Dragon").setScore(3);
		}

		if (p.hasKilledWither()) {
			objective.getScore(ChatColor.GREEN + "Wither").setScore(2);
		} else {
			objective.getScore("Wither").setScore(2);
		}

		if (p.hasKilledGuardian()) {
			objective.getScore(ChatColor.GREEN + "Guardian").setScore(1);
		} else {
			objective.getScore("Guardian").setScore(1);
		}
	}
	
	public void assignScoreboard(Player player) {
		player.setScoreboard(board);
	}
	
	public void assignScoreboard() {
		for (Player online : Bukkit.getOnlinePlayers()) {
			assignScoreboard(online);
		}
	}
}
