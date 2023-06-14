package me.sk8ingduck.friendsystemgui.pluginmessage;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Placeholder extends PlaceholderExpansion {

	private final HashMap<Player, Integer> friendsTotalCache = new HashMap<>();
	private final HashMap<Player, Integer> friendsOnlineCache = new HashMap<>();

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
		return "1.8";
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
		if (player == null) return "";

		//update cache
		FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player,
				friends -> {
					friendsTotalCache.put(player, friends.size());
					friendsOnlineCache.put(player, (int) friends.stream().filter(Friend::isOnline).count());
				});

		Integer result = null;
		if (params.equalsIgnoreCase("friendsTotal")) {
			result = friendsTotalCache.get(player);
		} else if (params.equalsIgnoreCase("friendsOnline")) {
			result = friendsOnlineCache.get(player);
		}

		result = result == null ? 0 : result;

		return result.toString();
	}
}
