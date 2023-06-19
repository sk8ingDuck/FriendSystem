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
        int rows = guiConfig.getSelectedPlayerGuiRows();

        FriendSystemGUI.getInstance().getPluginMessaging().getPlayerInfo(player, selectedPlayer, playerName,
                (areFriends, isFavourite, hasIncomingRequest, hasOutgoingRequest) ->
                        RyseInventory.builder()
                                .title(guiConfig.getSelectedPlayerGuiTitle().replaceAll("%PLAYER%", playerName))
                                .rows(rows)
                                .provider(new InventoryProvider() {
                                    @Override
                                    public void init(Player player, InventoryContents contents) {
                                        for (int i = 0; i < rows * 9; i++) {
                                            contents.set(i, guiConfig.getItem("selectedPlayerMenu.backgroundItem", false));
                                        }

                                        int backSlot = guiConfig.getSlot("selectedPlayerMenu.back");
                                        if (backSlot != -1) {
                                            contents.set(backSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.back"),
                                                    event -> GuiManager.guiMainMenu.open(player)));
                                        }

                                        if (areFriends) {
                                            int removeFriendSlot = guiConfig.getSlot("selectedPlayerMenu.removeFriend");
                                            if (removeFriendSlot != -1) {
                                                contents.set(removeFriendSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.removeFriend"),
                                                        clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "remove", playerName)));
                                            }

                                            int jumpSlot = guiConfig.getSlot("selectedPlayerMenu.jump");
                                            if (jumpSlot != -1) {
                                                contents.set(jumpSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.jump"),
                                                        clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "jump", playerName)));
                                            }

                                            int favouriteSlot = guiConfig.getSlot("selectedPlayerMenu." + (isFavourite ? "removeFavourite" : "addFavourite"));
                                            if (favouriteSlot != -1) {
                                                contents.set(favouriteSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu."
                                                                + (isFavourite ? "removeFavourite" : "addFavourite")),
                                                        event -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "favourite", playerName)));
                                            }
                                        } else if (hasIncomingRequest) {
                                            int acceptFriendSlot = guiConfig.getSlot("selectedPlayerMenu.acceptFriend");
                                            if (acceptFriendSlot != -1) {
                                                contents.set(acceptFriendSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.acceptFriend"),
                                                        clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "accept", playerName)));
                                            }

                                            int denyFriendSlot = guiConfig.getSlot("selectedPlayerMenu.denyFriend");
                                            if (denyFriendSlot != -1) {
                                                contents.set(denyFriendSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.denyFriend"),
                                                        clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "deny", playerName)));
                                            }
                                        } else if (hasOutgoingRequest) {
                                            int alreadyRequestedSlot = guiConfig.getSlot("selectedPlayerMenu.alreadyRequested");
                                            if (alreadyRequestedSlot != -1) {
                                                contents.set(alreadyRequestedSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.alreadyRequested"),
                                                        clickEvent -> player.closeInventory()));
                                            }
                                        } else {
                                            int addFriendSlot = guiConfig.getSlot("selectedPlayerMenu.addFriend");
                                            if (addFriendSlot != -1) {
                                                contents.set(addFriendSlot, IntelligentItem.of(guiConfig.getItem("selectedPlayerMenu.addFriend"),
                                                        clickEvent -> FriendSystemGUI.getInstance().getPluginMessaging()
                                                                .sendCommand(player, "add", playerName)));
                                            }
                                        }
                                    }
                                })
                                .build(FriendSystemGUI.getInstance())
                                .open(player)
        );
    }


}
