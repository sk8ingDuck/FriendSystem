package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.ProxyServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker
{
	private FriendSystem plugin;
	private int resourceId;

	public UpdateChecker(FriendSystem plugin, int resourceId) {
		this.plugin = plugin;
		this.resourceId = resourceId;
	}

	public void getLatestVersion(Consumer<String> consumer) {
		ProxyServer.getInstance().getScheduler().runAsync(plugin, () -> {
			try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
			     Scanner scanner = new Scanner(inputStream)) {
				if (scanner.hasNext()) {
					consumer.accept(scanner.next());
				}
			} catch (IOException exception) {
				this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
			}
		});
	}
}