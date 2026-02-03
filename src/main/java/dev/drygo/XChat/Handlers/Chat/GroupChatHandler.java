package dev.drygo.XChat.Handlers.Chat;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import dev.drygo.XChat.Utils.ChatUtils;

public class GroupChatHandler {

    private static LuckPerms luckPerms;

    public static void init() {
        luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
    }

    public static boolean sendGroupMessage(Player player, String message) {
        String formattedMessage = message;

        if (player.hasPermission("xchat.formatcolor")) {
            formattedMessage = ChatUtils.formatColor(message);
        }

        String group = getPrimaryGroup(player);
        if (group != null) {
            boolean sent = false;
            for (Player target : Bukkit.getOnlinePlayers()) {
                String targetGroup = getPrimaryGroup(target);
                if (targetGroup != null && targetGroup.equals(group)) {
                    target.sendMessage(ChatUtils.getMessage("chat.group", player)
                            .replace("%message%", formattedMessage));
                    sent = true;
                }
            }
            Bukkit.getConsoleSender().sendMessage(ChatUtils.getMessage("chat.group", player)
                    .replace("%message%", formattedMessage));
            return sent;
        }
        return false;
    }

    public static void sendGlobalMessage(Player player, String message) {
        String formattedMessage = message;

        if (player.hasPermission("xchat.formatcolor")) {
            formattedMessage = ChatUtils.formatColor(message);
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(ChatUtils.getMessage("chat.global", player)
                    .replace("%message%", formattedMessage));
        }
        Bukkit.getConsoleSender().sendMessage(ChatUtils.getMessage("chat.global", player)
                .replace("%message%", formattedMessage));
    }

    private static String getPrimaryGroup(Player player) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            return user.getPrimaryGroup();
        }
        return null;
    }
}
