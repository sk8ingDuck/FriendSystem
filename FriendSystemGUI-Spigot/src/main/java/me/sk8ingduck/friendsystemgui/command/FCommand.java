package me.sk8ingduck.friendsystemgui.command;

import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			GuiManager.guiMainMenu.open(player);
		}
		return true;
	}
}
