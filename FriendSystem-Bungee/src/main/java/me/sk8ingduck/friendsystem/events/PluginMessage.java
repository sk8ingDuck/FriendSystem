package me.sk8ingduck.friendsystem.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import me.sk8ingduck.friendsystem.utils.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class PluginMessage implements Listener {

	private final FriendManager fm;

	public PluginMessage() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		if (!event.getTag().equalsIgnoreCase("me:friendsystem") || !(event.getReceiver() instanceof ProxiedPlayer))
			return;

		ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
		String subChannel = in.readUTF();

		ProxiedPlayer receiver = (ProxiedPlayer) event.getReceiver();
		boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();

		if (subChannel.equalsIgnoreCase("toggleinvites")
				|| subChannel.equalsIgnoreCase("togglenotifies")
				|| subChannel.equalsIgnoreCase("togglemsgs")
				|| subChannel.equalsIgnoreCase("togglejump")) {

			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel);

		} else if (subChannel.equalsIgnoreCase("add")
				|| subChannel.equalsIgnoreCase("remove")
				|| subChannel.equalsIgnoreCase("deny")
				|| subChannel.equalsIgnoreCase("accept")
				|| subChannel.equals("jump")
				|| subChannel.equalsIgnoreCase("favourite")) {

			String playerName = in.readUTF();
			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel + " " + playerName);

		} else if (subChannel.equalsIgnoreCase("friendsList")) {

			ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
				FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("friendsList");
				out.writeUTF(receiver.getUniqueId().toString());
				out.writeInt(friendPlayer.getFriends().size());
				friendPlayer.getFriends().forEach((uuid, favourite) -> {
					ProxiedPlayer friend = ProxyServer.getInstance().getPlayer(uuid);
					FriendPlayer otherFriendPlayer = fm.getFriendPlayer(uuid);
					out.writeUTF(uuid);
					out.writeUTF(friend == null ? (onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid) : friend.getName());
					out.writeBoolean(friend != null);
					out.writeUTF(friend == null ? "offline" : friend.getServer().getInfo().getName());
					out.writeUTF(Util.formatDifference(otherFriendPlayer.getLastSeen()));
					out.writeUTF(otherFriendPlayer.getStatus() == null ? "" : otherFriendPlayer.getStatus());
					out.writeBoolean(favourite);
				});

				receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
			});

		} else if (subChannel.equalsIgnoreCase("requestsList")) {

			ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
				FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());

				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("requestsList");
				out.writeUTF(receiver.getUniqueId().toString());
				out.writeInt(friendPlayer.getRequests().size());
				friendPlayer.getRequests().forEach(uuid -> {
					out.writeUTF(uuid);
					out.writeUTF(onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid);
				});
				receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
			});

		} else if (subChannel.equalsIgnoreCase("playerInfo")) {

			ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
				String otherPlayerUuid = in.readUTF();
				String otherPlayerName = in.readUTF();

				String otherPlayerNameOrUuid = onlineMode ? otherPlayerUuid : otherPlayerName;

				FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
				FriendPlayer otherFriendPlayer = fm.getFriendPlayer(otherPlayerNameOrUuid);
				boolean areFriends = friendPlayer.getFriends().containsKey(otherPlayerNameOrUuid);
				boolean isFavourite = friendPlayer.getFriends().get(otherPlayerNameOrUuid) != null && friendPlayer.getFriends().get(otherPlayerNameOrUuid);
				boolean hasIncomingRequest = friendPlayer.getRequests().contains(otherPlayerNameOrUuid);
				boolean hasOutgoingRequest = otherFriendPlayer.getRequests().contains(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());

				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("playerInfo");
				out.writeUTF(receiver.getUniqueId().toString());
				out.writeBoolean(areFriends);
				out.writeBoolean(isFavourite);
				out.writeBoolean(hasIncomingRequest);
				out.writeBoolean(hasOutgoingRequest);
				receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
			});

		} else if (subChannel.equalsIgnoreCase("settings")) {

			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("settings");
			out.writeUTF(receiver.getUniqueId().toString());
			out.writeBoolean(friendPlayer.isInvitesAllowed());
			out.writeBoolean(friendPlayer.isNotifiesAllowed());
			out.writeBoolean(friendPlayer.isMsgsAllowed());
			out.writeBoolean(friendPlayer.isJumpingAllowed());
			receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());

		}
	}
}
