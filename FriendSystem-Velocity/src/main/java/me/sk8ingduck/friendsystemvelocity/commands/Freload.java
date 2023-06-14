package me.sk8ingduck.friendsystemvelocity.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import net.kyori.adventure.text.Component;

public class Freload implements SimpleCommand {

	@Override
	public void execute(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		FriendSystem.getInstance().reloadConfigs();
		source.sendMessage(Component.text("[FriendSystem] Configs reloaded!"));
	}
}
