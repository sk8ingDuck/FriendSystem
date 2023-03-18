package me.sk8ingduck.friendsystemgui.gui;

public class GuiManager {

    public static GuiMainMenu guiMainMenu;
    public static GuiSettings guiSettings;
    public static GuiRequests guiRequests;
    public static GuiSelectedPlayer guiSelectedPlayer;
    public static void init() {
        guiMainMenu = new GuiMainMenu();
        guiSettings = new GuiSettings();
        guiRequests = new GuiRequests();
        guiSelectedPlayer = new GuiSelectedPlayer();
    }


}
