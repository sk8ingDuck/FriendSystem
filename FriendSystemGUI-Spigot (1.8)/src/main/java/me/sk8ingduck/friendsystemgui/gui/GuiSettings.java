package me.sk8ingduck.friendsystemgui.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.mysql.MySQL;
import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

public class GuiSettings {

    private final ChestMenu gui;

    public GuiSettings() {
        GuiConfig guiConfig = FriendSystemGUI.getInstance().getSettingsConfig();
        MySQL mysql = FriendSystemGUI.getInstance().getMysql();

        gui = ChestMenu.builder(4).title(guiConfig.getSettingsGuiTitle()).build();

        for (int i = 0; i < 36; i++)
            gui.getSlot(i).setItem(ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.BLACK.getData(), " "));

        Slot back = gui.getSlot(10);
        Slot toggleInvites = gui.getSlot(13);
        Slot toggleNotifies = gui.getSlot(14);
        Slot toggleMsgs = gui.getSlot(15);
        Slot toggleJump = gui.getSlot(16);

        Slot currentInvites = gui.getSlot(22);
        Slot currentNotifies = gui.getSlot(23);
        Slot currentMsgs = gui.getSlot(24);
        Slot currentJump = gui.getSlot(25);


        back.setItem(guiConfig.get("gui.settingsMenu.item.back"));
        toggleInvites.setItem(guiConfig.get("gui.settingsMenu.item.toggleInvites"));
        toggleNotifies.setItem(guiConfig.get("gui.settingsMenu.item.toggleNotifies"));
        toggleMsgs.setItem(guiConfig.get("gui.settingsMenu.item.toggleMsgs"));
        toggleJump.setItem(guiConfig.get("gui.settingsMenu.item.toggleJump"));

        currentInvites.setItemTemplate(player -> guiConfig.get("gui.settingsMenu.item.invites" +
                (mysql.getOption(player.getUniqueId(), "invites") ? "On" : "Off")));

        currentNotifies.setItemTemplate(player -> guiConfig.get("gui.settingsMenu.item.notifies" +
                (mysql.getOption(player.getUniqueId(), "notifies") ? "On" : "Off")));

        currentMsgs.setItemTemplate(player -> guiConfig.get("gui.settingsMenu.item.msgs" +
                (mysql.getOption(player.getUniqueId(), "msgs") ? "On" : "Off")));

        currentJump.setItemTemplate(player -> guiConfig.get("gui.settingsMenu.item.jump" +
                (mysql.getOption(player.getUniqueId(), "jump") ? "On" : "Off")));


        toggleInvites.setClickHandler((player, clickInformation) -> {
            player.closeInventory();

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("toggleinvites");
            player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
        });

        toggleNotifies.setClickHandler((player, clickInformation) -> {
            player.closeInventory();

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("togglenotifies");
            player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
        });


        toggleMsgs.setClickHandler((player, clickInformation) -> {
            player.closeInventory();

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("togglemsgs");
            player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
        });

        toggleJump.setClickHandler((player, clickInformation) -> {
            player.closeInventory();

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("togglejump");
            player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
        });



        back.setClickHandler((player, clickInformation) -> GuiManager.guiMainMenu.open(player));
    }

    public void open(Player player) {
        gui.open(player);
    }
}
