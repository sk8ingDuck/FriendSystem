package me.sk8ingduck.friendsystemgui.pluginmessage;

public class Settings {
	private final boolean invites, notifies, msgs, jump;

	public Settings(boolean invites, boolean notifies, boolean msgs, boolean jump) {
		this.invites = invites;
		this.notifies = notifies;
		this.msgs = msgs;
		this.jump = jump;
	}

	public boolean isInvites() {
		return invites;
	}

	public boolean isNotifies() {
		return notifies;
	}

	public boolean isMsgs() {
		return msgs;
	}

	public boolean isJump() {
		return jump;
	}
}
