package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import me.sk8ingduck.friendsystemgui.util.Skull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;

public class GuiConfig extends Config {

    private final boolean guiEnabled;
    private final int guiSlot;
    private final boolean rightClickPlayerToOpenMenuEnabled;
    private final boolean canDropItem;
    private final boolean canMoveItemInInventory;
    private final String mainGuiTitle;
    private final String requestsGuiTitle;
    private final String settingsGuiTitle;
    private final String selectedPlayerGuiTitle;
    private final HashMap<String, ItemStack> items;

    public GuiConfig(String name, File path) {
        super(name, path);

        this.guiEnabled = (boolean) getPathOrSet("guiEnabled", true);
        this.guiSlot = (int) getPathOrSet("guiSlot", 8);
        this.rightClickPlayerToOpenMenuEnabled = (boolean) getPathOrSet("rightClickPlayerToOpenMenuEnabled", true);
        this.canDropItem = (boolean) getPathOrSet("canDropItem", false);
        this.canMoveItemInInventory = (boolean) getPathOrSet("canMoveItemInInventory", false);
        /*
        this.mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Friend menu");
        this.requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "§9Friend requests");
        this.settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cSettings");
        this.selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Selected player &7- &6%PLAYER%");

        this.items = new HashMap<>();
        items.put("guiItem", ItemUtil.createItem(Material.PLAYER_HEAD, "§aFriend menu"));
        items.put("gui.mainMenu.item.previousPage",Skull.LEFT.getSkull("§eprevious page"));
        items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("§enext page"));
        items.put("gui.mainMenu.item.settings", ItemUtil.createItem(Material.REDSTONE_TORCH, "§cSettings"));
        items.put("gui.mainMenu.item.requests", ItemUtil.createItem(Material.WRITABLE_BOOK, "§bRequests"));

        items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("§cZurück"));
        items.put("gui.settingsMenu.item.toggleInvites", ItemUtil.createItem(Material.TORCH, "§6toggle friend requests"));
        items.put("gui.settingsMenu.item.toggleNotifies", ItemUtil.createItem(Material.OAK_DOOR, "§6toggle join/leave messages"));
        items.put("gui.settingsMenu.item.toggleMsgs", ItemUtil.createItem(Material.WRITABLE_BOOK, "§6toggle private messages"));
        items.put("gui.settingsMenu.item.toggleJump", ItemUtil.createItem(Material.ENDER_PEARL, "§6toggle jumping"));

        items.put("gui.settingsMenu.item.invitesOn", ItemUtil.createItem(Material.LIME_STAINED_GLASS_PANE, "§6Friend requests are §aallowed"));
        items.put("gui.settingsMenu.item.invitesOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Friend requests are §cdisallowed"));
        items.put("gui.settingsMenu.item.notifiesOn", ItemUtil.createItem(Material.LIME_STAINED_GLASS_PANE, "§6Join/Leave messages are §aactivated"));
        items.put("gui.settingsMenu.item.notifiesOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Join/Leave messages are §cdisabled"));
        items.put("gui.settingsMenu.item.msgsOn", ItemUtil.createItem(Material.LIME_STAINED_GLASS_PANE, "§6Messages are §aallowed"));
        items.put("gui.settingsMenu.item.msgsOff",ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Messages are §cdisallowed"));
        items.put("gui.settingsMenu.item.jumpOn", ItemUtil.createItem(Material.LIME_STAINED_GLASS_PANE, "§6Jumping is §aallowed"));
        items.put("gui.settingsMenu.item.jumpOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Jumping §cdisallowed"));

        items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("§cBack"));
        items.put("gui.selectedPlayerMenu.item.addFriend", ItemUtil.createItem(Material.SUNFLOWER, "§6Send friend request"));
        items.put("gui.selectedPlayerMenu.item.removeFriend", ItemUtil.createItem(Material.BARRIER, "§cRemove friend"));
        items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemUtil.createItem(Material.SUNFLOWER, "§6Accept friend request"));
        items.put("gui.selectedPlayerMenu.item.denyFriend", ItemUtil.createItem(Material.BARRIER, "§cDecline friend request"));
        items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemUtil.createItem(Material.BARRIER, "§cPlayer already requested"));

        items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("§eprevious page"));
        items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("§enext page"));
        items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("§cback"));
        */


        this.mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Freunde Menü");
        this.requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "§9Freundschaftsanfragen");
        this.settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cEinstellungen");
        this.selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Spieler ausgewählt &7- &6%PLAYER%");

        this.items = new HashMap<>();
        items.put("guiItem", ItemUtil.createItem(Material.SKULL_ITEM, (short) 3, "§aFreunde Menü"));
        items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("§eNach links"));
        items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("§eNach rechts"));
        items.put("gui.mainMenu.item.settings", ItemUtil.createItem(Material.REDSTONE_TORCH_ON, "§cEinstellungen"));
        items.put("gui.mainMenu.item.requests", ItemUtil.createItem(Material.WRITTEN_BOOK, "§bFreundschaftsanfragen"));

        items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("§cZurück"));
        items.put("gui.settingsMenu.item.toggleInvites", ItemUtil.createItem(Material.TORCH, "§6Freundesanfragen aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleNotifies", ItemUtil.createItem(Material.WOOD_DOOR, "§6Join/Leave Nachrichten aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleMsgs", ItemUtil.createItem(Material.WRITTEN_BOOK, "§6Nachrichten aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleJump", ItemUtil.createItem(Material.ENDER_PEARL, "§6Nachspringen aktivieren / deaktiveren"));

        items.put("gui.settingsMenu.item.invitesOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 10, "§6Freundesanfragen sind §aaktiviert"));
        items.put("gui.settingsMenu.item.notifiesOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 10, "§6Join/Leave Nachrichten sind §aaktiviert"));
        items.put("gui.settingsMenu.item.msgsOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 10, "§6Nachrichten sind §aaktiviert"));
        items.put("gui.settingsMenu.item.jumpOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 10, "§6Nachspringen ist §aaktiviert"));
        items.put("gui.settingsMenu.item.invitesOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 1, "§6Freundesanfragen sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.notifiesOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 1, "§6Join/Leave Nachrichten sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.msgsOff",ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 1, "§6Nachrichten sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.jumpOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 1, "§6Nachspringen ist §cdeaktiviert"));

        items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("§cZurück"));
        items.put("gui.selectedPlayerMenu.item.addFriend", ItemUtil.createItem(Material.YELLOW_FLOWER, "§6Freundschaftsanfrage senden"));
        items.put("gui.selectedPlayerMenu.item.removeFriend", ItemUtil.createItem(Material.BARRIER, "§cFreund entfernen"));
        items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemUtil.createItem(Material.YELLOW_FLOWER, "§6Freundschaftsanfrage §aakzeptieren"));
        items.put("gui.selectedPlayerMenu.item.denyFriend", ItemUtil.createItem(Material.BARRIER, "§cFreundschaftsanfrage §cablehnen"));
        items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemUtil.createItem(Material.BARRIER, "§cSpieler bereits angefragt"));
        items.put("gui.selectedPlayerMenu.item.jump", ItemUtil.createItem(Material.ENDER_PEARL, "§cSpieler nachspringen"));

        items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("§eNach links"));
        items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("§eNach rechts"));
        items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("§cZurück"));

        items.forEach((itemPath, item) -> items.put(itemPath, (ItemStack) getPathOrSet(itemPath, item)));

    }

    public boolean isGuiEnabled() {
        return guiEnabled;
    }

    public int getGuiSlot() {
        return guiSlot;
    }

    public boolean isRightClickPlayerToOpenMenuEnabled() {
        return rightClickPlayerToOpenMenuEnabled;
    }

    public String getMainGuiTitle() {
        return mainGuiTitle;
    }

    public String getRequestsGuiTitle() {
        return requestsGuiTitle;
    }

    public String getSettingsGuiTitle() {
        return settingsGuiTitle;
    }

    public String getSelectedPlayerGuiTitle() {
        return selectedPlayerGuiTitle;
    }

    public ItemStack get(String path) {
        return items.get(path);
    }
}