package me.sk8ingduck.friendsystemvelocity.mysql;


import com.velocitypowered.api.plugin.annotation.DataDirectory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

public class MySQLDriver {
	private static final String MYSQL_VERSION = "8.0.30";
	private static final String MYSQL_SHA256 = "b5bf2f0987197c30adf74a9e419b89cda4c257da2d1142871f508416d5f2227a";
	private final Path dataDirectory;

	public MySQLDriver(@DataDirectory Path dataDirectory) throws Exception {
		this.dataDirectory = dataDirectory;

		if (ReflectUtil.getClass("com.mysql.cj.jdbc.Driver") != null) {
			return;
		}
		if (!Files.exists(dataDirectory)) {
			Files.createDirectories(dataDirectory);
		}
		if (loadCache()) {
			return;
		}
		downloadAndLoad();
	}

	private String getMySQLLibraryName() {
		return "mysql-connector-java-" + MYSQL_VERSION + ".jar";
	}

	private File getMySQLLiraryFile() {
		return new File(dataDirectory.toFile(), getMySQLLibraryName());
	}

	private String getMySQLDownloadUrl() {
		return "https://maven.aliyun.com/repository/public/mysql/mysql-connector-java/"
				.concat(MYSQL_VERSION)
				.concat("/")
				.concat(getMySQLLibraryName());
	}

	public boolean loadCache() throws Exception {
		File libraryFile = getMySQLLiraryFile();
		if (libraryFile.exists()) {
			if (getSha256(libraryFile).equals(MYSQL_SHA256)) {
				try {
					ReflectUtil.addFileLibrary(libraryFile);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	public void downloadAndLoad() throws Exception {
		File libraryFile = getMySQLLiraryFile();
		Path path = new DownloadTask(getMySQLDownloadUrl(), libraryFile.toPath()).call();
		if (!getSha256(path.toFile()).equals(MYSQL_SHA256)) {
			throw new RuntimeException("Checksum failed!");
		}
		try {
			ReflectUtil.addFileLibrary(libraryFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// 获得文件sha256
	private String getSha256(File file) throws Exception {
		try (FileInputStream fis = new FileInputStream(file);
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] buff = new byte[1024];
			int n;
			while ((n = fis.read(buff)) > 0) {
				baos.write(buff, 0, n);
			}
			final byte[] digest = MessageDigest.getInstance("SHA-256").digest(baos.toByteArray());
			StringBuilder sb = new StringBuilder();
			for (byte aByte : digest) {
				String temp = Integer.toHexString((aByte & 0xFF));
				if (temp.length() == 1) {
					sb.append("0");
				}
				sb.append(temp);
			}
			return sb.toString();
		}
	}
}