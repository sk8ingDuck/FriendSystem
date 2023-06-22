package me.sk8ingduck.friendsystemvelocity.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import me.sk8ingduck.friendsystemvelocity.util.FriendPlayer;
import me.sk8ingduck.friendsystemvelocity.util.UUIDFetcher;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class PluginMessage {
	private final FriendManager fm;

	public PluginMessage() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@Subscribe
	public void onPluginMessage(PluginMessageEvent event) {
		if (!event.getIdentifier().getId().equalsIgnoreCase("me:friendsystem")
				|| !(event.getTarget() instanceof Player))
			return;

		ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
		String subChannel = in.readUTF();

		Player receiver = (Player) event.getTarget();
		boolean onlineMode = FriendSystem.server.getConfiguration().isOnlineMode();

		if (subChannel.equalsIgnoreCase("toggleinvites")
				|| subChannel.equalsIgnoreCase("togglenotifies")
				|| subChannel.equalsIgnoreCase("togglemsgs")
				|| subChannel.equalsIgnoreCase("togglejump")) {

			FriendSystem.server.getCommandManager().executeAsync(receiver, "friend " + subChannel);

		} else if (subChannel.equalsIgnoreCase("add")
				|| subChannel.equalsIgnoreCase("remove")
				|| subChannel.equalsIgnoreCase("deny")
				|| subChannel.equalsIgnoreCase("accept")
				|| subChannel.equals("jump")
				|| subChannel.equalsIgnoreCase("favourite")
				|| subChannel.equalsIgnoreCase("status")) {

			String playerName = in.readUTF();
			FriendSystem.server.getCommandManager().executeAsync(receiver, "friend " + subChannel + " " + playerName);

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

		} else if (subChannel.equalsIgnoreCase("forwardcmd")) {



		}
	}

	private void sendFriendList(Player receiver, boolean onlineMode) {
		FriendSystem.server.getScheduler().buildTask(FriendSystem.getInstance(), () -> {
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("friendsList");
			out.writeUTF(receiver.getUniqueId().toString());
			out.writeInt(friendPlayer.getFriends().size());
			friendPlayer.getFriends().forEach((uuid, favourite) -> {
				FriendPlayer otherFriendPlayer = fm.getFriendPlayer(uuid);
				FriendSystem.getPlayer(uuid).ifPresent(onlineFriend -> {
					out.writeUTF(uuid);
					out.writeUTF(onlineFriend.getUsername());
					out.writeBoolean(true);
					out.writeUTF(onlineFriend.getCurrentServer().map(server -> server.getServerInfo().getName()).orElse("offline"));
					out.writeUTF(FriendSystem.getInstance().getMessagesConfig().formatDifference(otherFriendPlayer.getLastSeen()));
					out.writeUTF(otherFriendPlayer.getStatus() == null ? "" : otherFriendPlayer.getStatus());
					out.writeBoolean(favourite);
				});

				if (!FriendSystem.getPlayer(uuid).isPresent()) {
					out.writeUTF(uuid);
					out.writeUTF((onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid));
					out.writeBoolean(false);
					out.writeUTF("offline");
					out.writeUTF(FriendSystem.getInstance().getMessagesConfig().formatDifference(otherFriendPlayer.getLastSeen()));
					out.writeUTF(otherFriendPlayer.getStatus() == null ? "" : otherFriendPlayer.getStatus());
					out.writeBoolean(favourite);
				}
			});
			receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
		}).schedule();
	}

	private void sendRequestList(Player receiver, boolean onlineMode) {
		FriendSystem.server.getScheduler().buildTask(FriendSystem.getInstance(), () -> {
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("requestsList");
			out.writeUTF(receiver.getUniqueId().toString());
			out.writeInt(friendPlayer.getRequests().size());
			friendPlayer.getRequestsAndExpiracy().forEach((uuid, expiracy) -> {
				out.writeUTF(uuid);
				out.writeUTF(onlineMode ? UUIDFetcher.getName(UUID.fromString(uuid)) : uuid);
				out.writeUTF(expiracy.format(DateTimeFormatter.ofPattern(FriendSystem.getInstance().getSettingsConfig().getDateTimeFormat())));
				out.writeUTF(FriendSystem.getInstance().getMessagesConfig().formatDifferenceRequest(expiracy));
			});

			receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
		}).schedule();
	}

	private void sendPlayerInfo(Player receiver, boolean onlineMode, String otherPlayerUuid, String otherPlayerName) {
		FriendSystem.server.getScheduler().buildTask(FriendSystem.getInstance(), () -> {
			String otherPlayerNameOrUuid = onlineMode ? otherPlayerUuid : otherPlayerName;

			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());
			FriendPlayer otherFriendPlayer = fm.getFriendPlayer(otherPlayerNameOrUuid);

			boolean areFriends = friendPlayer.getFriends().containsKey(otherPlayerNameOrUuid);
			boolean isFavourite = friendPlayer.getFriends().get(otherPlayerNameOrUuid) != null && friendPlayer.getFriends().get(otherPlayerNameOrUuid);
			boolean hasIncomingRequest = friendPlayer.getRequests().contains(otherPlayerNameOrUuid);
			boolean hasOutgoingRequest = otherFriendPlayer.getRequests().contains(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("playerInfo");
			out.writeUTF(receiver.getUniqueId().toString());
			out.writeBoolean(areFriends);
			out.writeBoolean(isFavourite);
			out.writeBoolean(hasIncomingRequest);
			out.writeBoolean(hasOutgoingRequest);
			receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
		}).schedule();
	}

	private void sendPlayerSettings(Player receiver, boolean onlineMode) {
		FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("settings");
		out.writeUTF(receiver.getUniqueId().toString());
		out.writeBoolean(friendPlayer.isInvitesAllowed());
		out.writeBoolean(friendPlayer.isNotifiesAllowed());
		out.writeBoolean(friendPlayer.isMsgsAllowed());
		out.writeBoolean(friendPlayer.isJumpingAllowed());
		receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
	}

	private void sendOwnInfo(Player receiver, boolean onlineMode) {
		FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ownInfo");
		out.writeUTF(receiver.getUniqueId().toString());
		out.writeUTF(receiver.getCurrentServer().map(server -> server.getServerInfo().getName()).orElse("offline"));
		out.writeUTF(FriendSystem.getInstance().getMessagesConfig().formatDifference(friendPlayer.getLastSeen()));
		out.writeUTF(friendPlayer.getStatus() == null ? "" : friendPlayer.getStatus());
		receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
	}

	private void sendStatus(Player receiver, boolean onlineMode) {
		FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? receiver.getUniqueId().toString() : receiver.getUsername());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("status");
		out.writeUTF(receiver.getUniqueId().toString());
		out.writeUTF(friendPlayer.getStatus() == null ? "" : friendPlayer.getStatus());
		receiver.getCurrentServer().ifPresent(server -> server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray()));
	}

}
