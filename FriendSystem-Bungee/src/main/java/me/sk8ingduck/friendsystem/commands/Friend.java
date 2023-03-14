package me.sk8ingduck.friendsystem.commands;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Friend extends Command {
    private final FriendSystem fs = FriendSystem.getFriendSystem();

    private final MySQL mySQL = fs.getMySQL();

    private final MessagesConfig c = fs.getConfig();

    public Friend() {
        super("friend");
    }

    public void execute(CommandSender cs, String[] args) {
        if (!(cs instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) cs;
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            cs.sendMessage(new TextComponent(c.get("friend.help", false)));
            return;
        }
        UUID uuid = p.getUniqueId();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("toggleinvites")) {
                mySQL.getOption(uuid, "invites", value -> {
                    mySQL.setOptionAsync(uuid, "invites", !value);
                    p.sendMessage(new TextComponent(c.get("friend.toggleinvites." + (value ? "off" : "on"))));
                });
            } else if (args[0].equalsIgnoreCase("togglemsgs")) {
                mySQL.getOption(uuid, "msgs", value -> {
                    mySQL.setOptionAsync(uuid, "msgs", !value);
                    p.sendMessage(new TextComponent(c.get("friend.togglemsgs." + (value ? "off" : "on"))));
                });
            } else if (args[0].equalsIgnoreCase("togglejump")) {
                mySQL.getOption(uuid, "jump", value -> {
                    mySQL.setOptionAsync(uuid, "jump", !value);
                    p.sendMessage(new TextComponent(c.get("friend.togglejump." + (value ? "off" : "on"))));
                });
            } else if (args[0].equalsIgnoreCase("togglenotifies")) {
                mySQL.getOption(uuid, "notifies", value -> {
                    mySQL.setOptionAsync(uuid, "notifies", !value);
                    p.sendMessage(new TextComponent(c.get("friend.togglenotifies." + (value ? "off" : "on"))));
                });
            } else if (args[0].equalsIgnoreCase("list")) {
                mySQL.getFriendUUIDs(uuid, friends -> {
                    ArrayList<ProxiedPlayer> onlineFriends = new ArrayList<>();
                    ArrayList<UUID> offlineFriends = new ArrayList<>();
                    for (UUID friend : friends) {
                        ProxiedPlayer onlineFriend = ProxyServer.getInstance().getPlayer(friend);
                        if (onlineFriend == null) {
                            offlineFriends.add(friend);
                        } else {
                            onlineFriends.add(onlineFriend);
                        }
                    }

                    p.sendMessage(new TextComponent(c.get("friend.list.format.header", false)));

                    for (ProxiedPlayer online : onlineFriends) {
                        p.sendMessage(new TextComponent(c.get("friend.list.format.online", false)
                                .replaceAll("%PLAYERON%", online.getName())
                                .replaceAll("%SERVER%", online.getServer().getInfo().getName())));
                    }

                    for (UUID offline : offlineFriends) {
                        Future<String> nameFuture = UUIDFetcher.getNameFuture(offline);
                        try {
                            p.sendMessage(new TextComponent(c.get("friend.list.format.offline", false)
                                    .replaceAll("%PLAYEROFF%", nameFuture.get())));
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    p.sendMessage(new TextComponent(c.get("friend.list.format.footer", false)));
                });

            } else if (args[0].equalsIgnoreCase("requests")) {
                mySQL.getRequestUUIDs(uuid, requestUUIDs -> {
                    p.sendMessage(new TextComponent(c.get("friend.request.format.header", false)));
                    for (UUID requestUUID : requestUUIDs) {
                        Future<String> nameFuture = UUIDFetcher.getNameFuture(requestUUID);
                        try {
                            p.sendMessage(new TextComponent(c.get("friend.request.format.player", false)
                                    .replaceAll("%PLAYER%", nameFuture.get())));
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    p.sendMessage(new TextComponent(c.get("friend.request.format.footer", false)));
                });

            } else {
                p.sendMessage(new TextComponent(c.get("friend.help", false)));
                return;
            }
        }
        if (args.length != 2) {
            if (args[0].equalsIgnoreCase("add")
                    || args[0].equalsIgnoreCase("remove")
                    || args[0].equalsIgnoreCase("accept")
                    || args[0].equalsIgnoreCase("deny")
                    || args[0].equalsIgnoreCase("jump")) {
                p.sendMessage(new TextComponent(c.get("friend." + args[0] + ".syntax")));
                return;
            }
        }
        if (args.length == 2) {
            String playerName = args[1];

            if (playerName.equalsIgnoreCase(p.getName())) {
                p.sendMessage(new TextComponent(c.get("friend.error.interact")));
                return;
            }
            UUIDFetcher.getUUID(playerName, uuidOtherPlayer -> {
                if (uuidOtherPlayer == null) {
                    p.sendMessage(new TextComponent(c.get("friend.error.playernotfound")
                            .replaceAll("%PLAYER%", playerName)));
                    return;
                }
                mySQL.doesPlayerExist(uuidOtherPlayer, exists -> {
                    if (!exists) {
                        p.sendMessage(new TextComponent(c.get("friend.error.notonnetwork")
                                .replaceAll("%PLAYER%", playerName)));
                        return;
                    }
                    if (args[0].equalsIgnoreCase("add")) {
                        mySQL.arePlayersFriends(uuid, uuidOtherPlayer, areFriends -> {
                            if (areFriends) {
                                p.sendMessage(new TextComponent(c.get("friend.request.alreadyfriends")
                                        .replaceAll("%PLAYER%", playerName)));
                                return;
                            }
                            mySQL.isPlayerRequested(uuid, uuidOtherPlayer, alreadyRequested -> {
                                if (alreadyRequested) {
                                    p.sendMessage(new TextComponent(c.get("friend.request.alreadyrequested")
                                            .replaceAll("%PLAYER%", playerName)));
                                    return;
                                }
                                mySQL.isPlayerRequested(uuidOtherPlayer, uuid, alreadyReceivedRequest -> {
                                    if (alreadyReceivedRequest) {
                                        p.sendMessage(new TextComponent(c.get("friend.request.alreadyreceivedrequest")
                                                .replaceAll("%PLAYER%", playerName)));
                                        return;
                                    }

                                    mySQL.getOption(uuidOtherPlayer, "invites", invitesAllowed -> {
                                        if (!invitesAllowed) {
                                            p.sendMessage(new TextComponent(c.get("friend.request.invitestoggled")
                                                    .replaceAll("%PLAYER%", playerName)));
                                            return;
                                        }

                                        mySQL.addRequestAsync(uuidOtherPlayer, uuid);
                                        p.sendMessage(new TextComponent(c.get("friend.request.sent")
                                                .replaceAll("%PLAYER%", playerName)));
                                        trySendMessage(playerName, new TextComponent(c.get("friend.request.received")
                                                .replaceAll("%PLAYER%", p.getName())));
                                    });
                                });
                            });
                        });
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        mySQL.arePlayersFriends(uuid, uuidOtherPlayer, areFriends -> {
                            if (!areFriends) {
                                p.sendMessage(new TextComponent(c.get("friend.remove.notfriends")
                                        .replaceAll("%PLAYER%", playerName)));
                                return;
                            }
                            mySQL.removeFriendAsync(uuid, uuidOtherPlayer);
                            mySQL.removeFriendAsync(uuidOtherPlayer, uuid);
                            p.sendMessage(new TextComponent(c.get("friend.remove.successful")
                                    .replaceAll("%PLAYER%", playerName)));
                            trySendMessage(playerName, new TextComponent(c.get("friend.remove.successful2")
                                    .replaceAll("%PLAYER%", p.getName())));
                        });
                    } else if (args[0].equalsIgnoreCase("accept")) {
                        mySQL.isPlayerRequested(uuid, uuidOtherPlayer, isRequested -> {
                            if (!isRequested) {
                                p.sendMessage(new TextComponent(c.get("friend.add.norequest")
                                        .replaceAll("%PLAYER%", playerName)));
                                return;
                            }
                            mySQL.addFriendAsync(uuid, uuidOtherPlayer);
                            mySQL.addFriendAsync(uuidOtherPlayer, uuid);
                            mySQL.removeRequestAsync(uuid, uuidOtherPlayer);
                            p.sendMessage(new TextComponent(c.get("friend.add.successful")
                                    .replaceAll("%PLAYER%", playerName)));
                            trySendMessage(playerName, new TextComponent(c.get("friend.add.successful2")
                                    .replaceAll("%PLAYER%", p.getName())));
                        });
                    } else if (args[0].equalsIgnoreCase("deny")) {
                        mySQL.isPlayerRequested(uuid, uuidOtherPlayer, isRequested -> {
                            if (!isRequested) {
                                p.sendMessage(new TextComponent(c.get("friend.deny.norequest")
                                        .replaceAll("%PLAYER%", playerName)));
                                return;
                            }
                            mySQL.removeRequestAsync(uuid, uuidOtherPlayer);
                            p.sendMessage(new TextComponent(c.get("friend.deny.successful")
                                    .replaceAll("%PLAYER%", playerName)));
                            trySendMessage(playerName, new TextComponent(c.get("friend.deny.successful2")
                                    .replaceAll("%PLAYER%", p.getName())));
                        });
                    } else if (args[0].equalsIgnoreCase("jump")) {
                        mySQL.arePlayersFriends(uuid, uuidOtherPlayer, areFriends -> {
                            if (!areFriends) {
                                p.sendMessage(new TextComponent(c.get("friend.jump.notfriends")
                                        .replaceAll("%PLAYER%", playerName)));
                                return;
                            }
                            mySQL.getOption(uuidOtherPlayer, "jump", jumpAllowed -> {
                                if (!jumpAllowed) {
                                    p.sendMessage(new TextComponent(c.get("friend.jump.jumptoggled")
                                            .replaceAll("%PLAYER%", playerName)));
                                    return;
                                }
                                ProxiedPlayer p1 = ProxyServer.getInstance().getPlayer(args[1]);
                                if (p1 == null) {
                                    p.sendMessage(new TextComponent(c.get("friend.jump.notonline")
                                            .replaceAll("%PLAYER%", playerName)));
                                    return;
                                }
                                p.connect(p1.getServer().getInfo());
                            });
                        });
                    }
                });
            });
        }
    }

    private void trySendMessage(String username, TextComponent msg) {
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(username);
        if (pp != null) pp.sendMessage(msg);
    }

}
