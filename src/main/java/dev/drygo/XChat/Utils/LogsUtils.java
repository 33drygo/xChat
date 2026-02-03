package dev.drygo.XChat.Utils;

import org.bukkit.Bukkit;
import dev.drygo.XChat.XChat;

public class LogsUtils {
    private static XChat plugin;

    public static void init(XChat plugin) {
        LogsUtils.plugin = plugin;
    }

    public static void sendStartupMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#ffcc5c&lx&r&lChat #a0ff72plugin enabled!"));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dVersion: #ffffff" + plugin.version));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dDeveloped by: #ffffff" + String.join(", ", plugin.getDescription().getAuthors())));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
    }
    public static void sendShutdownMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#ffcc5c&lx&r&lChat #ff7272plugin disabled!"));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dVersion: #ffffff" + plugin.version));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor("#fff18dDeveloped by: #ffffff" + String.join(", ", plugin.getDescription().getAuthors())));
        Bukkit.getConsoleSender().sendMessage(ChatUtils.formatColor(" "));
    }
}
