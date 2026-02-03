package dev.drygo.XChat.Managers.Chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import dev.drygo.XChat.Handlers.Chat.GroupChatHandler;
import dev.drygo.XChat.Utils.SettingsUtils;

public class ChatManager implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.startsWith(SettingsUtils.getGlobalMsgSymbol())) {
            GroupChatHandler.sendGlobalMessage(player, message.substring(1));
            event.setCancelled(true);
            return;
        }

        if (SettingsUtils.getByPassPlayers().contains(String.valueOf(player)) || player.hasPermission("xchat.bypass")) {
            GroupChatHandler.sendGlobalMessage(player, message);
            event.setCancelled(true);
            return;
        }

        if (GroupChatHandler.sendGroupMessage(player, message)) {
        } else {
            GroupChatHandler.sendGlobalMessage(player, message);
        }

        event.setCancelled(true);
    }
}
