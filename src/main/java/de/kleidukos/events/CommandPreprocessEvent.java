package de.kleidukos.events;

import de.kleidukos.main.CommandBlocker;
import de.kleidukos.util.BlockedCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreprocessEvent implements Listener {

    @EventHandler
    private void onExecuteCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        for (BlockedCommand blocked : CommandBlocker.getInstance().commandManager.getBlockedCommands().values()) {
            if (parseCommand(event.getMessage(), blocked.getCommand()) || parseCommand(event.getMessage(), blocked.getAlias())) {
                if (!executeCommand(player, blocked)) {
                    if (blocked.getMessage().isEmpty()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandBlocker.getInstance().commandManager.getGlobalMessage()));
                    } else if (!blocked.getMessage().isEmpty() && !blocked.getMessage().equalsIgnoreCase("none")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', blocked.getMessage()));
                    } else if (blocked.getMessage().equalsIgnoreCase("none")) {
                        event.setCancelled(true);
                        return;
                    }
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                    return;
                }
            } else {
                continue;
            }
        }
    }

    private boolean parseCommand(String command, String check) {
        if (command.startsWith("/")) {
            return command.substring(1).equalsIgnoreCase(check);
        }

        String str = command.substring(1);
        if (str.startsWith("bukkit")) {
            return str.contains(check);
        }
        return false;
    }

    private boolean executeCommand(Player player, BlockedCommand command) {
        if (command.getCanOperatorExecute()) {
            if (player.isOp()) {
                return true;
            } else {
                if (player.isPermissionSet("BlockedCMD." + command.getPermission())) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (player.isPermissionSet("BlockedCMD." + command.getPermission())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
