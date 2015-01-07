package net.lnfinity.HardcoreChrono;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class HCScoreboard {

	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;

	private boolean run = false;

	private HardcoreChrono p;

	public HCScoreboard(HardcoreChrono plugin) {
		p = plugin;
	}

	public void startTimer() {
		run = true;
		createScoreboard();
	}

	public void stopTimer() {
		run = false;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getHours() {
		return hours;
	}

	public void createScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("hardcorechrono",
				"dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("§4§lHardcore Chrono");

		Score line = objective.getScore("");
		line.setScore(0);

		Score time = objective.getScore(p.getTimeString(hours) + ChatColor.GRAY
				+ ":" + ChatColor.WHITE + p.getTimeString(minutes)
				+ ChatColor.GRAY + ":" + ChatColor.WHITE
				+ p.getTimeString(seconds));
		time.setScore(-1);

		line = objective.getScore(" ");
		line.setScore(-2);

		if (p.hasKilledDragon()) {
			Score dragon = objective.getScore("§a§lDragon");
			dragon.setScore(-3);
		} else {
			Score dragon = objective.getScore("Dragon");
			dragon.setScore(-3);
		}

		if (p.hasKilledWither()) {
			Score wither = objective.getScore("§a§lWither");
			wither.setScore(-4);
		} else {
			Score wither = objective.getScore("Wither");
			wither.setScore(-4);
		}

		if (p.hasKilledGuardian()) {
			Score guardian = objective.getScore("§a§lGuardian");
			guardian.setScore(-5);
		} else {
			Score guardian = objective.getScore("Guardian");
			guardian.setScore(-5);
		}

		updateScoreboard(board);

		if (run) {
			new BukkitRunnable() {

				public void run() {
					if (run) {
						seconds++;
						if (seconds >= 60) {
							seconds = 0;
							minutes++;
						}
						if (minutes >= 60) {
							minutes = 0;
							hours++;
						}

						createScoreboard();

					}
				}
			}.runTaskLater(this.p, 20);
		}
	}

	public void updateScoreboard(Scoreboard sb) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.setScoreboard(sb);
		}
	}
}