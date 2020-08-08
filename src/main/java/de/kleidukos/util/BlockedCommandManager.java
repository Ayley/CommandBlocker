package de.kleidukos.util;

import de.kleidukos.main.CommandBlocker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BlockedCommandManager {

    private File file;
    private FileConfiguration cfg;

    private HashMap<String, BlockedCommand> blockedCommands = new HashMap<String, BlockedCommand>();

    public BlockedCommandManager() {
        file = new File("plugins/" + CommandBlocker.getInstance().getName(), "BlockedCommands.yml");
        cfg = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            cfg.set("help.Permission", "help");
            cfg.set("help.Operator", true);
            cfg.set("help.Message", "Leave the field blank for a global message.");
            cfg.set("help.Alias", "?");
            cfg.set("version.Permission", "version");
            cfg.set("version.Operator", false);
            cfg.set("version.Message", "");
            cfg.set("version.Alias", "");
            cfg.set("GlobalMessage", "Enter the global message here.");
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LoadBlockedCommands();
    }

    public void LoadBlockedCommands() {
        blockedCommands.clear();
        cfg = YamlConfiguration.loadConfiguration(file);
        for (String command : cfg.getKeys(false)) {

            if (command.equalsIgnoreCase("GlobalMessage")) {
                continue;
            }

            String permission = cfg.getString(command + ".Permission");
            String message = cfg.getString(command + ".Message");
            String alias = cfg.getString(command + ".Alias");
            Boolean canOperatorExecute = cfg.getBoolean(command + ".Operator");
            BlockedCommand blockedCommand = new BlockedCommand(command, alias == null ? "" : alias, permission, message == null ? "" : message, canOperatorExecute);

            if (!blockedCommands.containsKey(command)) {
                blockedCommands.put(command, blockedCommand);
            } else {
                continue;
            }
        }
    }

    public String getGlobalMessage() {
        return cfg.getString("GlobalMessage");
    }

    public HashMap<String, BlockedCommand> getBlockedCommands() {
        return blockedCommands;
    }
}
