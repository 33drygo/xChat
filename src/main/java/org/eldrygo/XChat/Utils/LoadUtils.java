package org.eldrygo.XChat.Utils;

import org.eldrygo.XChat.Handlers.Chat.GroupChatHandler;
import org.eldrygo.XChat.Handlers.Command.XChatCommand;
import org.eldrygo.XChat.Handlers.Command.XChatTabCompleter;
import org.eldrygo.XChat.Managers.Chat.ChatManager;
import org.eldrygo.XChat.Managers.ConfigManager;
import org.eldrygo.XChat.XChat;

public class LoadUtils {
    private final XChat plugin;
    private final ConfigManager configManager;
    private final SettingsUtils settingsUtils;
    private final GroupChatHandler groupChatHandler;
    private final ChatUtils chatUtils;

    public LoadUtils(XChat plugin, ConfigManager configManager, SettingsUtils settingsUtils, GroupChatHandler groupChatHandler, ChatUtils chatUtils) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.settingsUtils = settingsUtils;
        this.groupChatHandler = groupChatHandler;
        this.chatUtils = chatUtils;
    }

    public void loadFeatures() {
        loadConfigFiles();
        registerListeners();
        loadCommands();
    }
    public void registerListeners() {
        plugin.getServer().getPluginManager().registerEvents(new ChatManager(plugin, groupChatHandler, settingsUtils), plugin);
    }
    public void loadConfigFiles() {
        plugin.getLogger().info("Loading config files...");
        try {
            plugin.reloadConfig();
            plugin.config = plugin.getConfig();
            plugin.getLogger().info("✅ The config.yml file has been loaded successfully.");
        } catch (Exception e) {
            plugin.getLogger().severe("❌ Failed to reload plugin configuration due to an unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        configManager.reloadMessages();
        configManager.setPrefix(ChatUtils.formatColor(configManager.getMessageConfig().getString("prefix", "#ffcc5c&lx&r&lChat &cDefault Prefix &8»&r")));
    }
    public void loadCommands() {
        plugin.getCommand("xchat").setExecutor(new XChatCommand(plugin, chatUtils, configManager));
        plugin.getCommand("xchat").setTabCompleter(new XChatTabCompleter());
    }
}
