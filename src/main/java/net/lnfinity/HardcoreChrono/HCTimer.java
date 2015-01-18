package net.lnfinity.HardcoreChrono;

public class HCTimer {

	private HardcoreChrono p;
	private boolean run = false;
	private int task;

	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;

	public HCTimer(HardcoreChrono plugin) {
		p = plugin;
	}

	public void startTimer() {
		if(!run) {
			run = true;
			initializeTimer();
		}
	}

	public void stopTimer() {
		p.getServer().getScheduler().cancelTask(task);
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

	private void initializeTimer() {
		p.getScoreboard().updateDisplay();
		task = p.getServer().getScheduler().runTaskTimer(p, new Runnable() {
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
					p.getScoreboard().updateDisplay();
				}
			}
		}, 20L, 20L).getTaskId();
	}

}
