package org.eldrygo.XChat.Managers.Chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.eldrygo.XChat.Handlers.Chat.GroupChatHandler;
import org.eldrygo.XChat.Utils.SettingsUtils;
import org.eldrygo.XChat.XChat;

public class ChatManager implements Listener {

    private final XChat plugin;
    private final GroupChatHandler groupChatHandler;
    private final SettingsUtils settingsUtils;

    public ChatManager(XChat plugin, GroupChatHandler groupChatHandler, SettingsUtils settingsUtils) {
        this.groupChatHandler = groupChatHandler;
        this.plugin = plugin;
        this.settingsUtils = settingsUtils;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Mensaje global forzado
        if (message.startsWith(settingsUtils.getGlobalMsgSymbol())) {
            groupChatHandler.sendGlobalMessage(player, message.substring(1));
            event.setCancelled(true);
            return;
        }

        // Jugador con permiso para ignorar el sistema de grupos
        if (settingsUtils.getByPassPlayers().contains(String.valueOf(player)) || player.hasPermission("xchat.bypass")) {
            groupChatHandler.sendGlobalMessage(player, message);
            event.setCancelled(true);
            return;
        }

        // Intenta enviar mensaje de grupo
        if (groupChatHandler.sendGroupMessage(player, message)) {
        } else {
            groupChatHandler.sendGlobalMessage(player, message);
        }

        event.setCancelled(true);
    }
}
