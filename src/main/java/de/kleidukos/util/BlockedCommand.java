package de.kleidukos.util;

public class BlockedCommand {

    private String command;
    private String permission;
    private String message;
    private String alias;
    private boolean canOperatorExecute;

    public BlockedCommand(String command, String alias, String permission, String message, boolean canOperatorExecute) {
        this.command = command;
        this.alias = alias;
        this.permission = permission;
        this.message = message;
        this.canOperatorExecute = canOperatorExecute;
    }

    public String getCommand() {
        return command;
    }

    public String getAlias() {
        return alias;
    }

    public String getPermission() {
        return permission;
    }

    public String getMessage() {
        return message;
    }

    public boolean getCanOperatorExecute() {
        return canOperatorExecute;
    }
}
