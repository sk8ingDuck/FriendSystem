package me.sk8ingduck.friendsystemvelocity.config;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public abstract class Config {

	protected String filePath;
	protected YamlConfigurationLoader loader;

	protected CommentedConfigurationNode rootNode;

	public Config(String filePath, boolean copyDefault) {
		this.filePath = filePath;
		File file = new File(filePath);

		try {
			if (!file.exists()) {
				if (!copyDefault) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				} else {
					// Copy file from resources
					InputStream resource = getClass().getClassLoader().getResourceAsStream("settings.yml");
					if (resource == null) {
						throw new FileNotFoundException("Resource file " + filePath + " not found");
					}
					try (ReadableByteChannel inputChannel = Channels.newChannel(resource);
					     FileOutputStream output = new FileOutputStream(file);
					     WritableByteChannel outputChannel = Channels.newChannel(output)) {
						ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
						while (inputChannel.read(buffer) != -1) {
							buffer.flip();
							outputChannel.write(buffer);
							buffer.clear();
						}
					}
				}
			}

			loader = YamlConfigurationLoader
					.builder()
					.file(file)
					.nodeStyle(NodeStyle.BLOCK)
					.build();

			rootNode = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	protected void save() {
		try {
			loader.save(rootNode);
		} catch (ConfigurateException e) {
			e.printStackTrace();
		}
	}

	protected void reload() {
		try {
			rootNode = loader.load();
		} catch (ConfigurateException e) {
			e.printStackTrace();
		}
	}


}
