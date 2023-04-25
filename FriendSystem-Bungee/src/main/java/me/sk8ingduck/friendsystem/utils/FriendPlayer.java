package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendPlayer {

	private final String uuid;
	private boolean invitesAllowed;
	private boolean msgsAllowed;
	private boolean jumpingAllowed;
	private boolean notifiesAllowed;
	private LocalDateTime lastSeen;
	private String status;
	private final HashMap<String, Boolean> friends;
	private final ArrayList<String> requests;

	public FriendPlayer(String uuid,
	                    boolean invitesAllowed, boolean notifiesAllowed, boolean msgsAllowed, boolean jumpingAllowed,
	                    LocalDateTime lastSeen, String status, HashMap<String, Boolean> friends, ArrayList<String> requests) {
		this.uuid = uuid;
		this.invitesAllowed = invitesAllowed;
		this.notifiesAllowed = notifiesAllowed;
		this.msgsAllowed = msgsAllowed;
		this.jumpingAllowed = jumpingAllowed;
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
			ProxiedPlayer onlineFriend = FriendSystem.getPlayer(player);
			if (onlineFriend != null) {
				onlineFriends.put(onlineFriend, favourite);
			}
		});
		return onlineFriends;
	}

	public HashMap<String, Boolean> getOfflineFriends() {
		HashMap<String, Boolean> offlineFriends = new HashMap<>();
		friends.forEach((player, favourite) -> {
			if (FriendSystem.getPlayer(player) == null) {
				offlineFriends.put(player, favourite);
			}
		});
		return offlineFriends;
	}

	public boolean isFriendsWith(String uuid) {
		return friends.containsKey(uuid);
	}
	public boolean isRequestedBy(String uuid) {
		return requests.contains(uuid);
	}

	public void addRequest(String uuid) {
		requests.add(uuid);
	}
	public void removeRequest(String uuid) {
		requests.remove(uuid);
	}

	public void addFriend(String uuid) {
		friends.put(uuid, false);
	}
	public void removeFriend(String uuid) {
		friends.remove(uuid);
	}

	public void updateLastSeen() {
		lastSeen = LocalDateTime.now();
	}
	public void updateStatus(String status) {
		this.status = status;
	}
	public HashMap<String, Boolean> getFriends() {
		return friends;
	}
	public ArrayList<String> getRequests() {
		return requests;
	}

	public void sendMessage(BaseComponent[] message) {
		ProxiedPlayer player = FriendSystem.getPlayer(uuid);
		if (player != null)
			player.sendMessage(message);
	}
}
