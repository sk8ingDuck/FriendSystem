package me.sk8ingduck.friendsystemgui.config;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {

	private final File file;
	private final FileConfiguration fileConfiguration;
	protected String comment;

	public Config(String name, File path) {
		file = new File(path, name);

		if (!file.exists()) {
			path.mkdir();
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		fileConfiguration = new YamlConfiguration();

		try {
			fileConfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static String hex(String message) {
		if (message == null) return null;
		Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
		Matcher matcher = pattern.matcher(message);
		while (matcher.find()) {
			String hexCode = message.substring(matcher.start(), matcher.end());
			String replaceSharp = hexCode.replace('#', 'x');

			char[] ch = replaceSharp.toCharArray();
			StringBuilder builder = new StringBuilder("");
			for (char c : ch) {
				builder.append("&").append(c);
			}

			message = message.replace(hexCode, builder.toString());
			matcher = pattern.matcher(message);
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	protected File getFile() {
		return file;
	}

	protected FileConfiguration getFileConfiguration() {
		return fileConfiguration;
	}

	protected void save() {
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void saveComment() {
		try {
			List<String> lines = Files.readAllLines(file.toPath());
			if (lines.isEmpty() || !lines.get(0).startsWith("#")) {
				lines = new LinkedList<>(lines);
				lines.add(0, this.comment);

				// Write all lines back to the file
				try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
					for (String line : lines) {
						writer.write(line);
						writer.newLine();
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void reload() {
		try {
			fileConfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public Object getPathOrSet(String path, Object defaultValue) {
		return getPathOrSet(path, defaultValue, true);
	}

	public Object getPathOrSet(String path, Object defaultValue, boolean translateColors) {
		if (fileConfiguration.get(path) == null) {
			fileConfiguration.set(path, defaultValue);
			save();
		}

		return translateColors ? translateColors(fileConfiguration.get(path)) : fileConfiguration.get(path);
	}

	private Object translateColors(Object value) {
		if (value instanceof String) {
			return hex((String) value);
		}
		if (value instanceof ItemStack) {
			ItemStack itemStack = (ItemStack) value;
			ItemMeta itemMeta = itemStack.getItemMeta();

			if (itemMeta != null) {
				if (itemMeta.getDisplayName() != null) {
					itemMeta.setDisplayName(hex(itemMeta.getDisplayName()));
				}
				if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
					itemMeta.setLore(itemMeta.getLore().stream()
							.map(Config::hex)
							.collect(Collectors.toList()));
				}
				itemStack.setItemMeta(itemMeta);
			}
		}

		return value;
	}
}
