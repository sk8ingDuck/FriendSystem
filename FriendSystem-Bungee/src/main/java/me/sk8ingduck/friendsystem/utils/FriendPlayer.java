package me.sk8ingduck.friendsystem.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.LocalDateTime;
import java.util.*;

public class FriendPlayer {

	private final UUID uuid;
	private boolean invitesAllowed;
	private boolean msgsAllowed;
	private boolean jumpingAllowed;
	private boolean notifiesAllowed;
	private LocalDateTime lastSeen;
	private String status;
	private final HashMap<UUID, Boolean> friends;
	private final ArrayList<UUID> requests;

	public FriendPlayer(UUID uuid,
	                    boolean invitesAllowed, boolean msgsAllowed, boolean jumpingAllowed, boolean notifiesAllowed,
	                    LocalDateTime lastSeen, String status, HashMap<UUID, Boolean> friends, ArrayList<UUID> requests) {
		this.uuid = uuid;
		this.invitesAllowed = invitesAllowed;
		this.msgsAllowed = msgsAllowed;
		this.jumpingAllowed = jumpingAllowed;
		this.notifiesAllowed = notifiesAllowed;
		this.lastSeen = lastSeen;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public HashMap<ProxiedPlayer, Boolean> getOnlineFriends() {
		HashMap<ProxiedPlayer, Boolean> onlineFriends = new HashMap<>();
		friends.forEach((player, favourite) -> {
			ProxiedPlayer onlineFriend = ProxyServer.getInstance().getPlayer(player);
			if (onlineFriend != null) {
				onlineFriends.put(ProxyServer.getInstance().getPlayer(player), favourite);
			}
		});
		return onlineFriends;
	}

	public HashMap<UUID, Boolean> getOfflineFriends() {
		HashMap<UUID, Boolean> offlineFriends = new HashMap<>();
		friends.forEach((player, favourite) -> {
			if (ProxyServer.getInstance().getPlayer(player) == null) {
				offlineFriends.put(player, favourite);
			}
		});
		return offlineFriends;
	}

	public boolean isFriendsWith(UUID uuid) {
		return friends.containsKey(uuid);
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
		friends.put(uuid, false);
	}
	public void removeFriend(UUID uuid) {
		friends.remove(uuid);
	}

	public void updateLastSeen() {
		lastSeen = LocalDateTime.now();
	}
	public void updateStatus(String status) {
		this.status = status;
	}
	public HashMap<UUID, Boolean> getFriends() {
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
