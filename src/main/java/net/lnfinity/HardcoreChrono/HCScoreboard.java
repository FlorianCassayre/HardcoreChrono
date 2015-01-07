package net.lnfinity.HardcoreChrono;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class HCScoreboard {

	private HardcoreChrono p;
	private HCTimer t;
	
	public HCScoreboard(HardcoreChrono plugin) {
		p = plugin;
		
		t = new HCTimer(p);
	}

	public HCTimer getTimer() {
		return t;
	}

	public void updateDisplay() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("hardcorechrono",
				"dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Hardcore Chrono");

		Score line = objective.getScore("");
		line.setScore(0);

		Score time = objective.getScore(p.getTimeString(t.getHours())
				+ ChatColor.GRAY + ":" + ChatColor.WHITE
				+ p.getTimeString(t.getMinutes()) + ChatColor.GRAY + ":"
				+ ChatColor.WHITE + p.getTimeString(t.getSeconds()));
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

		updateScoreboard(board);

	}

	public void updateScoreboard(Scoreboard sb) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.setScoreboard(sb);
		}
	}
}
