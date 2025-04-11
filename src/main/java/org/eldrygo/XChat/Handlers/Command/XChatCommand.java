package org.eldrygo.XChat.Handlers.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.eldrygo.XChat.Managers.ConfigManager;
import org.eldrygo.XChat.Utils.ChatUtils;
import org.eldrygo.XChat.XChat;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class XChatCommand implements CommandExecutor {
    private final XChat plugin;
    private final ChatUtils chatUtils;
    private final ConfigManager configManager;

    public XChatCommand(XChat plugin, ChatUtils chatUtils, ConfigManager configManager) {
        this.chatUtils = chatUtils;
        this.plugin = plugin;
        this.configManager = configManager;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String action = args[0];

        switch (action) {
            case "reload" -> {
                handleReload(sender);
            }
        }
        return false;
    }

    private void handleReload(CommandSender sender) {
        try {
            try {
                plugin.reloadConfig();
                plugin.config = plugin.getConfig();
                plugin.getLogger().info("✅ The config.yml file has been reloaded successfully.");
            } catch (Exception e) {
                plugin.getLogger().severe("❌ Failed to reload plugin configuration due to an unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
                if (!messagesFile.exists()) {
                    plugin.saveResource("messages.yml", false);
                    plugin.getLogger().info("✅ The messages.yml file did not exist, it has been created.");
                } else {
                    plugin.getLogger().info("✅ The messages.yml file has been reloaded successfully.");
                }
                configManager.messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
                configManager.setPrefix(ChatUtils.formatColor(configManager.getMessageConfig().getString("prefix", "#ffcc5c&lx&r&lChat &cDefault Prefix &8»&r")));
            } catch (Exception e) {
                plugin.getLogger().severe("❌ Failed to load messages configuration due to an unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            sender.sendMessage(chatUtils.getMessage("command.reload.error", (Player) sender));
            return;
        }
        sender.sendMessage(chatUtils.getMessage("command.reload.success", (Player) sender));
    }
}
