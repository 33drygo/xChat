package dev.drygo.XChat.Utils;

import dev.drygo.XChat.XChat;

import java.util.List;

public class SettingsUtils {
    private static XChat plugin;

    public static void init(XChat plugin) {
        SettingsUtils.plugin = plugin;
    }

    public static String getGlobalMsgSymbol() {
        return plugin.getConfig().getString("settings.symbol_for_global_msg", "@");
    }
    public static List<String> getByPassPlayers() { return plugin.getConfig().getStringList("settings.bypass"); }
}
