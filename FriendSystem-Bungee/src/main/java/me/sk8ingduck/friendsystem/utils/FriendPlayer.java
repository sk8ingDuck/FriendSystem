package me.sk8ingduck.friendsystem.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FriendPlayer {

	private final UUID uuid;
	private boolean invitesAllowed;
	private boolean msgsAllowed;
	private boolean jumpingAllowed;
	private boolean notifiesAllowed;
	private LocalDateTime lastSeen;
	private final ArrayList<UUID> friends;
	private final ArrayList<UUID> requests;

	public FriendPlayer(UUID uuid,
	                    boolean invitesAllowed, boolean msgsAllowed, boolean jumpingAllowed, boolean notifiesAllowed,
	                    LocalDateTime lastSeen, ArrayList<UUID> friends, ArrayList<UUID> requests) {
		this.uuid = uuid;
		this.invitesAllowed = invitesAllowed;
		this.msgsAllowed = msgsAllowed;
		this.jumpingAllowed = jumpingAllowed;
		this.notifiesAllowed = notifiesAllowed;
		this.lastSeen = lastSeen;
		this.friends = friends;
		this.requests = requests;
	}

	public void toggleInvites() {
		invitesAllowed = !invitesAllowed;
	}

	public void toggleMsgs() {
		msgsAllowed = !msgsAllowed;
	}

	public void toggleJumping() {
		jumpingAllowed = !jumpingAllowed;
	}

	public void toggleNotifies() {
		notifiesAllowed = !notifiesAllowed;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	public boolean isInvitesAllowed() {
		return invitesAllowed;
	}

	public boolean isMsgsAllowed() {
		return msgsAllowed;
	}

	public boolean isJumpingAllowed() {
		return jumpingAllowed;
	}

	public boolean isNotifiesAllowed() {
		return notifiesAllowed;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public List<ProxiedPlayer> getOnlineFriends() {
		return friends.stream()
				.map(ProxyServer.getInstance()::getPlayer)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	public List<UUID> getOfflineFriends() {
		return friends.stream()
				.filter(friend -> ProxyServer.getInstance().getPlayer(friend) == null)
				.collect(Collectors.toList());
	}

	public boolean isFriendsWith(UUID uuid) {
		return friends.contains(uuid);
	}
	public boolean isRequestedBy(UUID uuid) {
		return requests.contains(uuid);
	}

	public void addRequest(UUID uuid) {
		requests.add(uuid);
	}
	public void removeRequest(UUID uuid) {
		requests.remove(uuid);
	}

	public void addFriend(UUID uuid) {
		friends.add(uuid);
	}
	public void removeFriend(UUID uuid) {
		friends.remove(uuid);
	}

	public ArrayList<UUID> getFriends() {
		return friends;
	}
	public ArrayList<UUID> getRequests() {
		return requests;
	}

	public void sendMessage(String message) {
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
		if (player != null)
			player.sendMessage(new TextComponent(message));
	}
}
