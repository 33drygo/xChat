package dev.drygo.XChat.Handlers.Command;

import dev.drygo.XChat.Managers.Chat.ChatBlockerManager;
import dev.drygo.XChat.Utils.LoadUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.drygo.XChat.Utils.ChatUtils;
import dev.drygo.XChat.XChat;
import org.jetbrains.annotations.NotNull;

public class XChatCommand implements CommandExecutor {
    private final XChat plugin;

    public XChatCommand(XChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        String action = args[0].toLowerCase();

        switch (action) {
            case "reload":
                handleReload(sender);
                break;

            case "toggle":
                handleToggle(sender);
                break;

            case "on":
            case "enable":
                handleEnable(sender);
                break;

            case "off":
            case "disable":
                handleDisable(sender);
                break;

            case "status":
                handleStatus(sender);
                break;

            case "clear":
                handleClear(sender);
                break;

            default:
                sendHelp(sender);
                break;
        }

        return true;
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("xchat.reload")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        try {
            LoadUtils.loadFiles();
            sender.sendMessage(ChatUtils.getMessage("commands.reload.success", null));
        } catch (Exception e) {
            sender.sendMessage(ChatUtils.getMessage("commands.reload.error", null));
            plugin.getLogger().severe("Error reloading config: " + e.getMessage());
        }
    }

    private void handleToggle(CommandSender sender) {
        if (!sender.hasPermission("xchat.blocker.toggle")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        ChatBlockerManager.toggleChat();

        if (ChatBlockerManager.isChatEnabled()) {
            sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.toggle.enabled", null));
        } else {
            sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.toggle.disabled", null));
        }
    }

    private void handleEnable(CommandSender sender) {
        if (!sender.hasPermission("xchat.blocker.toggle")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        if (ChatBlockerManager.isChatEnabled()) {
            sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.already_enabled", null));
            return;
        }

        ChatBlockerManager.setChatEnabled(true);
        sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.enabled", null));
    }

    private void handleDisable(CommandSender sender) {
        if (!sender.hasPermission("xchat.blocker.toggle")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        if (!ChatBlockerManager.isChatEnabled()) {
            sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.already_disabled", null));
            return;
        }

        ChatBlockerManager.setChatEnabled(false);
        sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.disabled", null));
    }

    private void handleStatus(CommandSender sender) {
        if (!sender.hasPermission("xchat.blocker.status")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        String status = ChatBlockerManager.isChatEnabled()
                ? ChatUtils.getMessage("chat_blocker.status.enabled", null)
                : ChatUtils.getMessage("chat_blocker.status.disabled", null);

        sender.sendMessage(ChatUtils.getMessage("chat_blocker.commands.status", null)
                .replace("%status%", status));
    }

    private void handleClear(CommandSender sender) {
        if (!sender.hasPermission("xchat.blocker.clear")) {
            sender.sendMessage(ChatUtils.getMessage("commands.no_permission", null));
            return;
        }

        for (int i = 0; i < 100; i++) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission("xchat.blocker.bypass.clear")) {
                    player.sendMessage(" ");
                }
            }
        }

        Bukkit.broadcastMessage(ChatUtils.getMessage("chat_blocker.commands.clear.success", null)
                .replace("%player%", sender.getName()));
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatUtils.formatColor("&8&m                                          "));
        sender.sendMessage(ChatUtils.formatColor("&6&lXChat &7- &eCommand Help"));
        sender.sendMessage(ChatUtils.formatColor(""));

        if (sender.hasPermission("xchat.reload")) {
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat reload &f- Reload configuration"));
        }

        if (sender.hasPermission("xchat.blocker.toggle")) {
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat toggle &f- Toggle chat on/off"));
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat on &f- Enable chat"));
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat off &f- Disable chat"));
        }

        if (sender.hasPermission("xchat.blocker.status")) {
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat status &f- Check chat status"));
        }

        if (sender.hasPermission("xchat.blocker.clear")) {
            sender.sendMessage(ChatUtils.formatColor("  &7/xchat clear &f- Clear chat"));
        }

        sender.sendMessage(ChatUtils.formatColor("&8&m                                          "));
    }
}