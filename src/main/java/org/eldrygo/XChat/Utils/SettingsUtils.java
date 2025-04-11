package org.eldrygo.XChat.Utils;

import org.eldrygo.XChat.XChat;

import java.util.List;

public class SettingsUtils {
    private final XChat plugin;

    public SettingsUtils(XChat plugin) {
        this.plugin = plugin;
    }

    public String getGlobalMsgSymbol() {
        return plugin.getConfig().getString("settings.symbol_for_global_msg", "@");
    }
    public List<String> getByPassPlayers() { return plugin.getConfig().getStringList("settings.bypass"); }
}
