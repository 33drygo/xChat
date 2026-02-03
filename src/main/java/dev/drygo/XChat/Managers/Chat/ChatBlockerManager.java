package dev.drygo.XChat.Managers.Chat;

import org.bukkit.entity.Player;

public class ChatBlockerManager {
    private static boolean chatEnabled = true;

    public static boolean isChatEnabled() {
        return chatEnabled;
    }

    public static void setChatEnabled(boolean enabled) {
        chatEnabled = enabled;
    }

    public static void toggleChat() {
        setChatEnabled(!chatEnabled);
    }

    public static boolean canPlayerChat(Player player) {
        if (player.hasPermission("xchat.blocker.bypass") || player.isOp()) {
            return true;
        }
        return chatEnabled;
    }
}