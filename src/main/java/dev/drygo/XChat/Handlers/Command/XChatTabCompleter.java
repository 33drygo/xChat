package dev.drygo.XChat.Handlers.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XChatTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            List<String> subcommands = Arrays.asList("reload", "toggle", "on", "off", "enable", "disable", "status", "clear");

            for (String sub : subcommands) {
                if (sub.startsWith(args[0].toLowerCase())) {
                    if (hasPermissionForSubcommand(sender, sub)) {
                        completions.add(sub);
                    }
                }
            }

            return completions;
        }

        return completions;
    }

    private boolean hasPermissionForSubcommand(CommandSender sender, String subcommand) {
        return switch (subcommand) {
            case "reload" -> sender.hasPermission("xchat.reload");
            case "toggle", "on", "off", "enable", "disable" -> sender.hasPermission("xchat.blocker.toggle");
            case "status" -> sender.hasPermission("xchat.blocker.status");
            case "clear" -> sender.hasPermission("xchat.blocker.clear");
            default -> true;
        };
    }
}