package me.sk8ingduck.friendsystem.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

//			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel);
			FriendSystem.getInstance().getFriendCommand().execute(receiver, new String[]{subChannel});

		} else if (subChannel.equalsIgnoreCase("add")
				|| subChannel.equalsIgnoreCase("remove")
				|| subChannel.equalsIgnoreCase("deny")
				|| subChannel.equalsIgnoreCase("accept")
				|| subChannel.equals("jump")
				|| subChannel.equalsIgnoreCase("favourite")
				|| subChannel.equalsIgnoreCase("status")) {

			String playerName = in.readUTF();
//			ProxyServer.getInstance().getPluginManager().dispatchCommand(receiver, "friend " + subChannel + " " + playerName);
			FriendSystem.getInstance().getFriendCommand().execute(receiver, new String[]{subChannel, playerName});

		} else if (subChannel.equalsIgnoreCase("friendsList")) {
			sendFriendList(receiver, onlineMode);
		} else if (subChannel.equalsIgnoreCase("requestsList")) {
			sendRequestList(receiver, onlineMode);
		} else if (subChannel.equalsIgnoreCase("playerInfo")) {
			String otherPlayerUuid = in.readUTF();
			String otherPlayerName = in.readUTF();
			sendPlayerInfo(receiver, onlineMode, otherPlayerUuid, otherPlayerName);
		} else if (subChannel.equalsIgnoreCase("settings")) {
			sendPlayerSettings(receiver, onlineMode);
		} else if (subChannel.equalsIgnoreCase("ownInfo")) {
			sendOwnInfo(receiver, onlineMode);
		} else if (subChannel.equalsIgnoreCase("getstatus")) {
			sendStatus(receiver, onlineMode);
		}
	}

	private void sendFriendList(ProxiedPlayer receiver, boolean onlineMode) {
		ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("friendsList");
			out.writeUTF(receiver.getUniqueId().toString());
			out.writeInt(friendPlayer.getFriends().size());
			friendPlayer.getFriends().forEach((uuid, favourite) -> {
				ProxiedPlayer friend = FriendSystem.getPlayer(uuid);
				FriendPlayer otherFriendPlayer = fm.getFriendPlayer(uuid);
				out.writeUTF(uuid);
				out.writeUTF(friend == null ? (onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid) : friend.getName());
				out.writeBoolean(friend != null);
				out.writeUTF(friend == null ? "offline" : friend.getServer().getInfo().getName());
				out.writeUTF(FriendSystem.getInstance().getConfig().formatDifference(otherFriendPlayer.getLastSeen()));
				out.writeUTF(otherFriendPlayer.getStatus() == null ? "" : otherFriendPlayer.getStatus());
				out.writeBoolean(favourite);
			});

			receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
		});

	}

	private void sendRequestList(ProxiedPlayer receiver, boolean onlineMode) {
		ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("requestsList");
			out.writeUTF(receiver.getUniqueId().toString());
			HashMap<String, LocalDateTime> requests = friendPlayer.getRequestsAndExpiracy();
			out.writeInt(requests.size());
			requests.forEach((uuid, expiracy) -> {
				out.writeUTF(uuid);
				out.writeUTF(onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid);
				out.writeUTF(expiracy.format(DateTimeFormatter.ofPattern(FriendSystem.getInstance().getSettingsConfig().getDateTimeFormat())));
				out.writeUTF(FriendSystem.getInstance().getConfig().formatDifferenceRequest(expiracy));
			});
			receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
		});
	}

	private void sendPlayerInfo(ProxiedPlayer receiver, boolean onlineMode, String otherPlayerUuid, String otherPlayerName) {
		ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {


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
	}

	private void sendPlayerSettings(ProxiedPlayer receiver, boolean onlineMode) {
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

	private void sendOwnInfo(ProxiedPlayer receiver, boolean onlineMode) {
		FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ownInfo");
		out.writeUTF(receiver.getUniqueId().toString());
		out.writeUTF(receiver.getServer().getInfo().getName());
		out.writeUTF(FriendSystem.getInstance().getConfig().formatDifference(friendPlayer.getLastSeen()));
		out.writeUTF(friendPlayer.getStatus() == null ? "" : friendPlayer.getStatus());
		receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
	}

	private void sendStatus(ProxiedPlayer receiver, boolean onlineMode) {
		FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getName());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("status");
		out.writeUTF(receiver.getUniqueId().toString());
		out.writeUTF(friendPlayer.getStatus() == null ? "" : friendPlayer.getStatus());
		receiver.getServer().getInfo().sendData("me:friendsystem", out.toByteArray());
	}




}
