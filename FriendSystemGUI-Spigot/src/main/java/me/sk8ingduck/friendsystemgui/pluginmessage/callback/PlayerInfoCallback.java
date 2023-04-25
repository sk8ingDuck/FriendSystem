package me.sk8ingduck.friendsystemgui.pluginmessage.callback;

public interface PlayerInfoCallback {
	void onReceive(boolean areFriends, boolean isFavourite, boolean hasIncomingRequest, boolean hasOutgoingRequest);
}
