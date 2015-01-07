package net.lnfinity.HardcoreChrono;

import org.bukkit.scheduler.BukkitRunnable;

public class HCTimer {

	private HardcoreChrono p;
	private boolean run = false;

	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;

	public HCTimer(HardcoreChrono plugin) {
		p = plugin;
	}

	public void startTimer() {
		run = true;
		eachSecond();
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

	private void eachSecond() {
		p.getScoreboard().updateDisplay();
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
					
					eachSecond();

				}
			}
		}.runTaskLater(this.p, 20);
	}

}

