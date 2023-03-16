package me.sk8ingduck.friendsystemgui.pluginmessage;

public interface SettingsCallback {
	void onReceive(boolean invites, boolean notifies, boolean msgs, boolean jump);
}
