package org.eldrygo.XChat.Utils;

import org.bukkit.Bukkit;
import org.eldrygo.XChat.XChat;

public class LogsUtils {
    private XChat plugin;
    private String version;

    public LogsUtils(XChat plugin) {
        this.plugin = plugin;
        this.version = plugin.getDescription().getVersion();
    }

    public void sendStartupMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#ffcc5c&lx&r&lChat #a0ff72plugin enabled!"));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dVersion: #ffffff" + version));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dDeveloped by: #ffffff" + String.join(", ", plugin.getDescription().getAuthors())));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
    }
    public void sendShutdownMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#ffcc5c&lx&r&lChat #ff7272plugin disabled!"));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dVersion: #ffffff" + version));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dDeveloped by: #ffffff" + String.join(", ", plugin.getDescription().getAuthors())));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
    }
}
