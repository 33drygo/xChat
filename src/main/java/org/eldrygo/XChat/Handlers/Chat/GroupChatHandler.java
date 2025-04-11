package org.eldrygo.XChat.Handlers.Chat;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.eldrygo.XChat.Utils.ChatUtils;

public class GroupChatHandler {

    private final LuckPerms luckPerms;
    private final ChatUtils chatUtils;

    public GroupChatHandler(ChatUtils chatUtils) {
        this.chatUtils = chatUtils;
        this.luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
    }

    public boolean sendGroupMessage(Player player, String message) {
        String group = getPrimaryGroup(player);
        if (group != null) {
            boolean sent = false;
            for (Player target : Bukkit.getOnlinePlayers()) {
                String targetGroup = getPrimaryGroup(target);
                if (targetGroup != null && targetGroup.equals(group)) {
                    target.sendMessage(chatUtils.getMessage("chat.group", player)
                            .replace("%message%", message));
                    sent = true;
                }
            }
            return sent;
        }
        return false;
    }

    public void sendGlobalMessage(Player player, String message) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(chatUtils.getMessage("chat.global", player)
                    .replace("%message%", message));
        }
    }

    // Obtiene el nombre del grupo principal del jugador
    private String getPrimaryGroup(Player player) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            return user.getPrimaryGroup();
        }
        return null;
    }
}
