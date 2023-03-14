package me.sk8ingduck.friendsystem.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class PluginMessage implements Listener {

	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		if (!event.getTag().equalsIgnoreCase("me:friendsystem") || !(event.getReceiver() instanceof ProxiedPlayer))
			return;

		ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
		String subChannel = in.readUTF();

		ProxiedPlayer receiver = (ProxiedPlayer) event.getReceiver();
		if (subChannel.equalsIgnoreCase("toggleinvites")
				|| subChannel.equalsIgnoreCase("togglenotifies")
				|| subChannel.equalsIgnoreCase("togglemsgs")
				|| subChannel.equalsIgnoreCase("togglejump")) {
			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel);
		} else if (subChannel.equalsIgnoreCase("add")
				|| subChannel.equalsIgnoreCase("remove")
				|| subChannel.equalsIgnoreCase("deny")
				|| subChannel.equalsIgnoreCase("accept")
				|| subChannel.equals("jump")) {
			String playerName = in.readUTF();
			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel + " " + playerName);

		} else if (subChannel.equalsIgnoreCase("friendsList")) {
			FriendSystem.getFriendSystem().getMySQL().getFriendUUIDs(receiver.getUniqueId(), friendUUIDs -> {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("friendsList");
				out.writeUTF(receiver.getUniqueId().toString());
				out.writeInt(friendUUIDs.size());

				for (UUID uuid : friendUUIDs) {
					ProxiedPlayer friend = ProxyServer.getInstance().getPlayer(uuid);
					out.writeUTF(uuid.toString());
					out.writeUTF(friend != null ? friend.getName() : UUIDFetcher.getName(uuid));
					out.writeBoolean(friend != null);
				}
				receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
			});
		} else if (subChannel.equalsIgnoreCase("requestsList")) {
			FriendSystem.getFriendSystem().getMySQL().getRequestUUIDs(receiver.getUniqueId(), requestUUIDs -> {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("requestsList");
				out.writeUTF(receiver.getUniqueId().toString());
				out.writeInt(requestUUIDs.size());

				for (UUID uuid : requestUUIDs) {
					ProxiedPlayer friend = ProxyServer.getInstance().getPlayer(uuid);
					out.writeUTF(uuid.toString());
					out.writeUTF(friend != null ? friend.getName() : UUIDFetcher.getName(uuid));
					out.writeBoolean(friend != null);
				}

				receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
			});
		}
	}
}
