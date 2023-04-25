package me.sk8ingduck.friendsystem.config;

import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MessagesConfig extends Config {

	private static final String COMPONENT_REGEX
			= "(.+?)\\s*\\{(?:(?:hovertext:\\s*(.+?)(?:,\\s*)?)?(?:command:\\s*(.+?))?(?:,\\s*hovertext:\\s*(.+?))?)?}";
	private static final String COMPONENT_PATH_REGEX = "\\{(.+?)}";
	protected final LinkedHashMap<String, String> messages;

	public MessagesConfig(String name, String path) {
		super(name, path);

		this.messages = new LinkedHashMap<>();
		loadMessages();
		loadTextComponents();

		messages.forEach((messagePath, message) -> messages.put(messagePath, (String) getPathOrSet(messagePath, message)));
	}

	public abstract void loadMessages();

	public abstract void loadTextComponents();

	public TextComponent parseChatComponent(String message, String... replacements) {
		if (message == null) return null;

		// Pattern to match the format: TEXT {hovertext: TEXT, command: /command}
		Pattern pattern = Pattern.compile(COMPONENT_REGEX);
		Matcher matcher = pattern.matcher(message);

		if (!matcher.find()) return null;

		String text = replacePlaceholders(matcher.group(1), replacements);
		String hoverText = replacePlaceholders(matcher.group(2) != null ? matcher.group(2) : matcher.group(4), replacements);
		String command = replacePlaceholders(matcher.group(3), replacements);

		TextComponent textComponent = new TextComponent(text);

		if (command != null)
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

		if (hoverText != null)
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', hoverText)).create()));

		return textComponent;
	}

	public BaseComponent[] get(String path, boolean prefix, String... replacements) {
		List<BaseComponent> finalComponents = new ArrayList<>();

		// Add the prefix if requested
		if (prefix) {
			finalComponents.add(new TextComponent(messages.get("prefix")));
		}

		String content = replacePlaceholders(messages.get(path), replacements);

		// Check if content contains pattern {path.to.textcomponent}
		Pattern pattern = Pattern.compile(COMPONENT_PATH_REGEX);
		Matcher matcher = pattern.matcher(content);

		int lastIndex = 0;

		// Replace all occurrences of the pattern with the corresponding TextComponent
		while (matcher.find()) {
			String beforeMatch = content.substring(lastIndex, matcher.start());
			String textComponentPath = matcher.group(1);
			lastIndex = matcher.end();

			// Replace all replacements in the text before the match
			beforeMatch = replacePlaceholders(beforeMatch, replacements);

			// Add the text before the match
			if (!beforeMatch.isEmpty()) {
				finalComponents.add(new TextComponent(beforeMatch));
			}

			// Add the TextComponent from parseChatComponent
			TextComponent textComponent = parseChatComponent(messages.get("textcomponents." + textComponentPath), replacements);
			if (textComponent != null)
				finalComponents.add(textComponent);
			else
				FriendSystem.getInstance().getLogger().info("§c[FriendSystem] Failed to parse TextComponent {" + textComponentPath + "}. " +
						"Most likely is that it has a wrong format! Format should be:\n" +
						"Your text {hovertext: Your text on hover, command: /yourCommand}");
		}

		// Add the remaining text after the last match
		String afterLastMatch = content.substring(lastIndex);
		if (!afterLastMatch.isEmpty()) {
			finalComponents.add(new TextComponent(afterLastMatch));
		}

		return finalComponents.toArray(new BaseComponent[0]);
	}

	public BaseComponent[] get(String path, String... replacements) {
		return get(path, true, replacements);
	}

	private String replacePlaceholders(String text, String... replacements) {
		for (int i = 0; i < replacements.length; i += 2) {
			String toReplace = replacements[i];
			String replacement = replacements[i + 1];
			text = text.replaceAll(toReplace, replacement);
		}
		return text;
	}

	public String formatDifference(LocalDateTime start) {
		Duration duration = Duration.between(start, LocalDateTime.now());
		long years = duration.toDays() / 365;
		long days = duration.toDays() % 365;
		long hours = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;
		long seconds = duration.getSeconds() % 60;


		if (years > 0) {
			return years + " " + (messages.get("friend.timeformat.year" + (years > 1 ? "s" : "")))
					+ (days > 0 ? " " + days + " " + (messages.get("friend.timeformat.day" + (days > 1 ? "s" : ""))) : "");
		}
		if (days > 0) {
			return days + " " + (messages.get("friend.timeformat.day" + (days > 1 ? "s" : "")))
					+ (hours > 0 ? " " + hours + " " + (messages.get("friend.timeformat.hour" + (hours > 1 ? "s" : ""))) : "");
		}
		if (hours > 0) {
			return hours + " " + (messages.get("friend.timeformat.hour" + (hours > 1 ? "s" : "")))
					+ (minutes > 0 ? " " + minutes + " " + (messages.get("friend.timeformat.minute" + (minutes > 1 ? "s" : ""))) : "");
		}
		if (minutes > 0) {
			return minutes + " " + (messages.get("friend.timeformat.minute" + (minutes > 1 ? "s" : "")))
					+ (seconds > 0 ? " " + seconds + " " + (messages.get("friend.timeformat.second" + (seconds > 1 ? "s" : ""))) : "");
		}
		if (seconds > 0) {
			return seconds + " " + (messages.get("friend.timeformat.second" + (seconds > 1 ? "s" : "")));
		}
		return "invalid";
	}

}
