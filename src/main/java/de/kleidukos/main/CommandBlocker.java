package de.kleidukos.main;

import de.kleidukos.commands.ReloadCommandsCMD;
import de.kleidukos.events.CommandPreprocessEvent;
import de.kleidukos.util.BlockedCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandBlocker extends JavaPlugin {

    private static CommandBlocker instance;
    public BlockedCommandManager commandManager;

    public static CommandBlocker getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        commandManager = new BlockedCommandManager();

        Bukkit.getPluginManager().registerEvents(new CommandPreprocessEvent(), this);
        getCommand("CommandBlocker").setExecutor(new ReloadCommandsCMD());
    }

    @Override
    public void onDisable() {

    }
}
