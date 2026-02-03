package dev.drygo.XChat.Utils;

import dev.drygo.XChat.Handlers.Chat.GroupChatHandler;
import dev.drygo.XChat.Handlers.Command.XChatCommand;
import dev.drygo.XChat.Handlers.Command.XChatTabCompleter;
import dev.drygo.XChat.Managers.Chat.ChatManager;
import dev.drygo.XChat.Managers.ConfigManager;
import dev.drygo.XChat.XChat;

public class LoadUtils {
    private static XChat plugin;

    public static void loadFeatures() {
        loadFiles();
        registerListeners();
        loadCommands();
    }

    public static void registerListeners() {
        plugin.getServer().getPluginManager().registerEvents(new ChatManager(), plugin);
    }

    public static void loadFiles() {
        ConfigManager.loadConfig();
        ConfigManager.reloadMessages();
        ConfigManager.setPrefix(ConfigManager.getMessageConfig().getString("prefix"));
    }

    public static void loadCommands() {
        plugin.getCommand("xchat").setExecutor(new XChatCommand(plugin));
        plugin.getCommand("xchat").setTabCompleter(new XChatTabCompleter());
    }

    public static void init(XChat plugin) {
        LoadUtils.plugin = plugin;
    }
}
