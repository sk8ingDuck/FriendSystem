package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.Config;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FriendPlayer {

	private final String uuid;
	private boolean invitesAllowed;
	private boolean msgsAllowed;
	private boolean jumpingAllowed;
	private boolean notifiesAllowed;
	private LocalDateTime lastSeen;
	private String status;
	private final HashMap<String, Boolean> friends;
	private final HashMap<String, LocalDateTime> requests;

	public FriendPlayer(String uuid,
	                    boolean invitesAllowed, boolean notifiesAllowed, boolean msgsAllowed, boolean jumpingAllowed,
	                    LocalDateTime lastSeen, String status, HashMap<String, Boolean> friends, HashMap<String, LocalDateTime> requests) {
		this.uuid = uuid;
		this.invitesAllowed = invitesAllowed;
		this.notifiesAllowed = notifiesAllowed;
		this.msgsAllowed = msgsAllowed;
		this.jumpingAllowed = jumpingAllowed;
		this.lastSeen = lastSeen;
		this.status = Config.hex(status);
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
		return getRequests().contains(uuid);
	}

	public void addRequest(String uuid) {
		requests.put(uuid, LocalDateTime.now());
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
		this.status = Config.hex(status);
	}
	public HashMap<String, Boolean> getFriends() {
		return friends;
	}

	public HashMap<String, LocalDateTime> getRequestsAndExpiracy() {
		if (FriendSystem.getInstance().getSettingsConfig().isRequestDurationEnabled()) {
		LocalDateTime maxDurationAgo = LocalDateTime.now()
				.minusMinutes(FriendSystem.getInstance().getSettingsConfig().getRequestDuration());

		Iterator<Map.Entry<String, LocalDateTime>> iterator = requests.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, LocalDateTime> entry = iterator.next();
			if (entry.getValue().isBefore(maxDurationAgo)) {
				FriendSystem.getInstance().getMySQL().removeRequestAsync(uuid, entry.getKey());
				iterator.remove();
			}
		}
	}

		return requests;
	}

	public ArrayList<String> getRequests() {
		return new ArrayList<>(getRequestsAndExpiracy().keySet());
	}

	public void sendMessage(BaseComponent[] message) {
		ProxiedPlayer player = FriendSystem.getPlayer(uuid);
		if (player != null)
			player.sendMessage(message);
	}
}
