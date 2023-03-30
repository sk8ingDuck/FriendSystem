package me.sk8ingduck.friendsystemgui.pluginmessage;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class PluginMessage implements PluginMessageListener {

	private final HashMap<String, Consumer<List<Friend>>> friendsListCallbacks = new HashMap<>();
	private final HashMap<String, Consumer<List<Request>>> requestsListCallbacks = new HashMap<>();
	private final HashMap<String, PlayerInfoCallback> playerInfoCallbacks = new HashMap<>();
	private final HashMap<String, SettingsCallback> settingsCallbacks = new HashMap<>();


	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("me:friendsystem")) {
			return;
		}

		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (!subchannel.equalsIgnoreCase("friendsList")
				&& !subchannel.equalsIgnoreCase("requestsList")
				&& !subchannel.equalsIgnoreCase("playerInfo")
				&& !subchannel.equalsIgnoreCase("settings")) {
			return;
		}
		String uuid = in.readUTF();
		if (subchannel.equals("friendsList")) {

			if (!friendsListCallbacks.containsKey(uuid)) return;
			int size = in.readInt();
			ArrayList<Friend> friends = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				friends.add(new Friend(in.readUTF(), in.readUTF(), in.readBoolean(),
						in.readUTF(), in.readUTF(), in.readUTF(), in.readBoolean()));
			}

			friendsListCallbacks.get(uuid).accept(friends);
			friendsListCallbacks.remove(uuid);

		} else if (subchannel.equals("requestsList")) {

			if (!requestsListCallbacks.containsKey(uuid)) return;
			int size = in.readInt();

			ArrayList<Request> requests = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				requests.add(new Request(in.readUTF(), in.readUTF()));
			}

			requestsListCallbacks.get(uuid).accept(requests);
			requestsListCallbacks.remove(uuid);

		} else if (subchannel.equalsIgnoreCase("playerInfo")) {

			if (!playerInfoCallbacks.containsKey(uuid)) return;
			playerInfoCallbacks.get(uuid).onReceive(in.readBoolean(), in.readBoolean(), in.readBoolean(), in.readBoolean());
			playerInfoCallbacks.remove(uuid);

		} else if (subchannel.equalsIgnoreCase("settings")) {

			if (!settingsCallbacks.containsKey(uuid)) return;
			settingsCallbacks.get(uuid).onReceive(in.readBoolean(), in.readBoolean(), in.readBoolean(), in.readBoolean());
			settingsCallbacks.remove(uuid);

		}
	}

	public void getFriends(Player player, Consumer<List<Friend>> friendlist) {

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("friendsList");
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		friendsListCallbacks.put(player.getUniqueId().toString(), friendlist);
	}

	public void getRequests(Player player, Consumer<List<Request>> requestlist) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("requestsList");
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		requestsListCallbacks.put(player.getUniqueId().toString(), requestlist);
	}

	public void getPlayerInfo(Player player, String uuid, String playerName, PlayerInfoCallback playerInfoCallback) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("playerInfo");
		out.writeUTF(uuid);
		out.writeUTF(playerName);
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		playerInfoCallbacks.put(player.getUniqueId().toString(), playerInfoCallback);
	}

	public void getSettings(Player player, SettingsCallback settingsCallback) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("settings");
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		settingsCallbacks.put(player.getUniqueId().toString(), settingsCallback);
	}

}

