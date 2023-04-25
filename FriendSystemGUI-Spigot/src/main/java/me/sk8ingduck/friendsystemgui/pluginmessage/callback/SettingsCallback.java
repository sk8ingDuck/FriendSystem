package me.sk8ingduck.friendsystemgui.pluginmessage.callback;

public interface SettingsCallback {
	void onReceive(boolean invites, boolean notifies, boolean msgs, boolean jump);
}
