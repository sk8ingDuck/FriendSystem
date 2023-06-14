package me.sk8ingduck.friendsystemgui.gui;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import org.bukkit.entity.Player;

public class GuiSelectedPlayer {

    public void open(Player player, String selectedPlayer, String playerName) {
        GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();

        FriendSystemGUI.getInstance().getPluginMessaging().getPlayerInfo(player, selectedPlayer, playerName,
                (areFriends, isFavourite, hasIncomingRequest, hasOutgoingRequest) ->
                        RyseInventory.builder()
                                .title(guiConfig.getSelectedPlayerGuiTitle().replaceAll("%PLAYER%", playerName))
                                .rows(1)
                                .provider(new InventoryProvider() {
                                    @Override
                                    public void init(Player player, InventoryContents contents) {
                                        contents.set(0, 0, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.back"),
                                                event -> GuiManager.guiMainMenu.open(player)));

                                        if (areFriends) {
                                            contents.set(0, 8, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.removeFriend"),
                                                    clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "remove", playerName)));
                                            contents.set(0, 3, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.jump"),
                                                    clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "jump", playerName)));
                                            contents.set(0, 5, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu."
                                                            + (isFavourite ? "removeFavourite" : "addFavourite")),
                                                    event -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "favourite", playerName)));
                                        } else if (hasIncomingRequest) {
                                            contents.set(0, 3, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.acceptFriend"),
                                                    clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "accept", playerName)));
                                            contents.set(0, 5, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.denyFriend"),
                                                    clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "deny", playerName)));
                                        } else if (hasOutgoingRequest) {
                                            contents.set(0, 4, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.alreadyRequested"),
                                                    clickEvent -> player.closeInventory()));
                                        } else {
                                            contents.set(0, 4, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.addFriend"),
                                                    clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                            .sendCommand(player, "add", playerName)));
                                        }
                                    }
                                })
                                .build(FriendSystemGUI.getInstance())
                                .open(player)
        );
    }


}
