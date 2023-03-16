package me.sk8ingduck.friendsystemgui.pluginmessage;

public interface PlayerInfoCallback {
	void onReceive(boolean areFriends, boolean hasIncomingRequest, boolean hasOutgoingRequest);
}
