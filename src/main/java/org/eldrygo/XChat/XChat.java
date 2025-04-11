package org.eldrygo.XChat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.eldrygo.XChat.Handlers.Chat.GroupChatHandler;
import org.eldrygo.XChat.Managers.ConfigManager;
import org.eldrygo.XChat.Utils.ChatUtils;
import org.eldrygo.XChat.Utils.LoadUtils;
import org.eldrygo.XChat.Utils.LogsUtils;
import org.eldrygo.XChat.Utils.SettingsUtils;

public class XChat extends JavaPlugin {
    public FileConfiguration config;
    public String prefix;
    private LoadUtils loadUtils;
    private LogsUtils logsUtils;
    private ChatUtils chatUtils;
    private ConfigManager configManager;
    private SettingsUtils settingsUtils;
    private GroupChatHandler groupChatHandler;

    public void onEnable() {
        this.logsUtils = new LogsUtils(this);
        this.configManager = new ConfigManager(this);
        this.chatUtils = new ChatUtils(this, configManager);
        this.settingsUtils = new SettingsUtils(this);
        this.groupChatHandler = new GroupChatHandler(chatUtils);
        this.settingsUtils = new SettingsUtils(this);
        this.loadUtils = new LoadUtils(this, configManager, settingsUtils, groupChatHandler, chatUtils);
        loadUtils.loadFeatures();
        logsUtils.sendStartupMessage();
    }
    public void onDisable() {
        logsUtils.sendShutdownMessage();
    }
}
