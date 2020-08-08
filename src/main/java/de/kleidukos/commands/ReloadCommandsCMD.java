package de.kleidukos.commands;

import de.kleidukos.main.CommandBlocker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommandsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CommandBlocker.getInstance().commandManager.LoadBlockedCommands();
        sender.sendMessage("[CommandBlocker] Successful Commands reloaded...");

        return false;
    }
}
