package net.lnfinity.HardcoreChrono;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HCCommands implements CommandExecutor {

	HardcoreChrono p;

	public HCCommands(HardcoreChrono plugin) {
		p = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (alias.equalsIgnoreCase("hc")) {
			if (args.length != 0) {
				switch (args[0]) {
				case "start":
					p.getScoreboard().getTimer().startTimer();
					sender.sendMessage(ChatColor.GREEN + "Timer started !");
					break;
				case "stop":
					p.getScoreboard().getTimer().stopTimer();
					sender.sendMessage(ChatColor.GREEN + "Timer stoped !");
					break;

				default:
					sender.sendMessage("/hc <start|stop>");
				}
				return true;
			}
		}
		return false;
	}

}
