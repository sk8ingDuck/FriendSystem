package me.sk8ingduck.friendsystemgui.util;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PluginMessaging implements PluginMessageListener {

	private final HashMap<UUID, BungeeCallback> bungeeCallbacks = new HashMap<>();

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("me:friendsystem")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("friendsList")) {
			UUID uuid = UUID.fromString(in.readUTF());
			int size = in.readInt();
			ArrayList<BungeePlayer> bungeePlayers = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				bungeePlayers.add(new BungeePlayer(
						UUID.fromString(in.readUTF()), in.readUTF(), in.readBoolean(), in.readUTF(), in.readUTF()));
			}
			if (bungeeCallbacks.get(uuid) == null) {
				Bukkit.getConsoleSender().sendMessage("Invalid friend-list callback for " + uuid);
				return;
			}

			bungeeCallbacks.get(uuid).onReceive(bungeePlayers);
			bungeeCallbacks.remove(uuid);
		} else if (subchannel.equals("requestsList")) {
			UUID uuid = UUID.fromString(in.readUTF());
			int size = in.readInt();
			ArrayList<BungeePlayer> bungeePlayers = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				bungeePlayers.add(new BungeePlayer(
						UUID.fromString(in.readUTF()), in.readUTF(), false, null, null));
			}
			if (bungeeCallbacks.get(uuid) == null) {
				Bukkit.getConsoleSender().sendMessage("Invalid friend-request callback for " + uuid);
				return;
			}

			bungeeCallbacks.get(uuid).onReceive(bungeePlayers);
			bungeeCallbacks.remove(uuid);
		}
	}

	public void getFriends(Player player, BungeeCallback friends) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("friendsList");
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		bungeeCallbacks.put(player.getUniqueId(), friends);
	}

	public void getRequests(Player player, BungeeCallback friends) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("requestsList");
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		bungeeCallbacks.put(player.getUniqueId(), friends);
	}

}

