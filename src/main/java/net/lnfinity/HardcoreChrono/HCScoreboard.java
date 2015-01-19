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

		Score line = objective.getScore("");
		line.setScore(0);

		Score time = objective.getScore(formatter.format(t.getHours())
				+ ChatColor.GRAY + ":" + ChatColor.WHITE
				+ formatter.format(t.getMinutes()) + ChatColor.GRAY + ":"
				+ ChatColor.WHITE + formatter.format(t.getSeconds()));
		time.setScore(-1);

		line = objective.getScore(" ");
		line.setScore(-2);

		if (p.hasKilledDragon()) {
			Score dragon = objective.getScore(ChatColor.GREEN + "Dragon");
			dragon.setScore(-3);
		} else {
			Score dragon = objective.getScore("Dragon");
			dragon.setScore(-3);
		}

		if (p.hasKilledWither()) {
			Score wither = objective.getScore(ChatColor.GREEN + "Wither");
			wither.setScore(-4);
		} else {
			Score wither = objective.getScore("Wither");
			wither.setScore(-4);
		}

		if (p.hasKilledGuardian()) {
			Score guardian = objective.getScore(ChatColor.GREEN + "Guardian");
			guardian.setScore(-5);
		} else {
			Score guardian = objective.getScore("Guardian");
			guardian.setScore(-5);
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
