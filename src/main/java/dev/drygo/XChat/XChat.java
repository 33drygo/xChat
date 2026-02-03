package dev.drygo.XChat;

import dev.drygo.XChat.Managers.Chat.ChatManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import dev.drygo.XChat.Handlers.Chat.GroupChatHandler;
import dev.drygo.XChat.Managers.ConfigManager;
import dev.drygo.XChat.Utils.ChatUtils;
import dev.drygo.XChat.Utils.LoadUtils;
import dev.drygo.XChat.Utils.LogsUtils;
import dev.drygo.XChat.Utils.SettingsUtils;

public class XChat extends JavaPlugin {
    public String version;
    public String prefix;

    public void onEnable() {
        ConfigManager.init(this);
        ChatUtils.init(this);
        LoadUtils.init(this);
        LoadUtils.loadFeatures();
        SettingsUtils.init(this);
        GroupChatHandler.init();
        LogsUtils.init(this);
        LogsUtils.sendStartupMessage();
    }
    public void onDisable() {
        LogsUtils.sendShutdownMessage();
    }
}
