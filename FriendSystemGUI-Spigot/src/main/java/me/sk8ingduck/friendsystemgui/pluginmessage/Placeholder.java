package me.sk8ingduck.friendsystemgui.pluginmessage;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.pluginmessage.callback.SettingsCallback;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Placeholder extends PlaceholderExpansion {

	private final HashMap<UUID, Integer> friendsTotalCache = new HashMap<>();
	private final HashMap<UUID, Integer> friendsOnlineCache = new HashMap<>();
	private final HashMap<UUID, Settings> settingsCache = new HashMap<>();

	@Override
	public @NotNull String getIdentifier() {
		return "friendsystem";
	}

	@Override
	public @NotNull String getAuthor() {
		return "sk8ingDuck";
	}

	@Override
	public @NotNull String getVersion() {
		return "2.0";
	}

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public boolean persist() {
		return true;
	}


	@Override
	public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
		if (player == null) return "Invalid player";

		//update cache
		FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player,
				friends -> {
					friendsTotalCache.put(player.getUniqueId(), friends.size());
					friendsOnlineCache.put(player.getUniqueId(), (int) friends.stream().filter(Friend::isOnline).count());
				});
		FriendSystemGUI.getInstance().getPluginMessaging().getSettings(player,
				(invites, notifies, msgs, jump) -> {
					settingsCache.put(player.getUniqueId(), new Settings(invites, notifies, msgs, jump));
				});

		UUID playerId = player.getUniqueId();

		switch (params.toLowerCase()) {
			case "friendstotal":
				return Optional.ofNullable(friendsTotalCache.get(playerId)).map(Object::toString).orElse("Loading...");
			case "friendsonline":
				return Optional.ofNullable(friendsOnlineCache.get(playerId)).map(Object::toString).orElse("Loading...");
			case "isinvitesenabled":
				return Optional.ofNullable(settingsCache.get(playerId)).map(s -> Boolean.toString(s.isInvites())).orElse("Loading...");
			case "isnotifiesenabled":
				return Optional.ofNullable(settingsCache.get(playerId)).map(s -> Boolean.toString(s.isNotifies())).orElse("Loading...");
			case "ismsgsenabled":
				return Optional.ofNullable(settingsCache.get(playerId)).map(s -> Boolean.toString(s.isMsgs())).orElse("Loading...");
			case "jumpenabled":
				return Optional.ofNullable(settingsCache.get(playerId)).map(s -> Boolean.toString(s.isJump())).orElse("Loading...");
			default:
				return "Not found";
		}
	}
}
