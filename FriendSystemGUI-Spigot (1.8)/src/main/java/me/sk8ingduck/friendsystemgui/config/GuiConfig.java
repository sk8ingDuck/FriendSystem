package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import me.sk8ingduck.friendsystemgui.util.Skull;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;

public class GuiConfig extends Config {

    private final boolean guiEnabled;
    private final int guiSlot;
    private final boolean rightClickPlayerToOpenMenuEnabled;

    private final String mainGuiTitle;
    private final String requestsGuiTitle;
    private final String settingsGuiTitle;
    private final String selectedPlayerGuiTitle;
    private final HashMap<String, ItemStack> items;

    public GuiConfig(String name, File path) {
        super(name, path);

        this.guiEnabled = (boolean) getPathOrSet("guiEnabled", true);
        this.guiSlot = (int) getPathOrSet("guiSlot", 8);
        this.rightClickPlayerToOpenMenuEnabled = (Boolean) getPathOrSet("rightClickPlayerToOpenMenuEnabled", true);

        this.mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Freunde Menü");
        this.requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "§9Freundschaftsanfragen");
        this.settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cEinstellungen");
        this.selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Spieler ausgewählt &7- &6%PLAYER%");

        this.items = new HashMap<>();
        items.put("guiItem", ItemUtil.createItem(Material.SKULL_ITEM, (byte) 3, "§aFreunde Menü"));
        items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("§eNach links"));
        items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("§eNach rechts"));
        items.put("gui.mainMenu.item.settings", ItemUtil.createItem(Material.REDSTONE_TORCH_ON, "§cEinstellungen"));
        items.put("gui.mainMenu.item.requests", ItemUtil.createItem(Material.BOOK_AND_QUILL, "§bFreundschaftsanfragen"));

        items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("§cZurück"));
        items.put("gui.settingsMenu.item.toggleInvites", ItemUtil.createItem(Material.TORCH, "§6Freundesanfragen aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleNotifies", ItemUtil.createItem(Material.WOOD_DOOR, "§6Join/Leave Nachrichten aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleMsgs",ItemUtil.createItem(Material.PAPER, "§6Nachrichten aktivieren / deaktiveren"));
        items.put("gui.settingsMenu.item.toggleJump", ItemUtil.createItem(Material.ENDER_PEARL, "§6Nachspringen aktivieren / deaktiveren"));

        items.put("gui.settingsMenu.item.invitesOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.LIME.getData(), "§6Freundesanfragen sind §aaktiviert"));
        items.put("gui.settingsMenu.item.invitesOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.RED.getData(), "§6Freundesanfragen sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.notifiesOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.LIME.getData(), "§6Join/Leave Nachrichten sind §aaktiviert"));
        items.put("gui.settingsMenu.item.notifiesOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.RED.getData(), "§6Join/Leave Nachrichten sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.msgsOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.LIME.getData(), "§6Nachrichten sind §aaktiviert"));
        items.put("gui.settingsMenu.item.msgsOff", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.RED.getData(), "§6Nachrichten sind §cdeaktiviert"));
        items.put("gui.settingsMenu.item.jumpOn", ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.LIME.getData(), "§6Nachspringen ist §aaktiviert"));
        items.put("gui.settingsMenu.item.jumpOff",ItemUtil.createItem(Material.STAINED_GLASS_PANE, DyeColor.RED.getData(), "§6Nachspringen ist §cdeaktiviert"));

        items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("§cZurück"));
        items.put("gui.selectedPlayerMenu.item.addFriend", ItemUtil.createItem(Material.GREEN_RECORD, "§6Freundschaftsanfrage senden"));
        items.put("gui.selectedPlayerMenu.item.removeFriend", ItemUtil.createItem(Material.BARRIER, "§cFreund entfernen"));
        items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemUtil.createItem(Material.GREEN_RECORD, "§6Freundschaftsanfrage §aakzeptieren"));
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