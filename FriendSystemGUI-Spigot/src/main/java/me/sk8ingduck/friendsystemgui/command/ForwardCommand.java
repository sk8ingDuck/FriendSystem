package me.sk8ingduck.friendsystemgui.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForwardCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("forwardcmd");
			out.writeUTF(String.join(" ", args));
			((Player) sender).sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		}
		return true;
	}
}
