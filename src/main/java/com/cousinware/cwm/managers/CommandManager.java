package com.cousinware.cwm.managers;

import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.command.commands.Set;
import com.cousinware.cwm.command.commands.Toggle;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

public class CommandManager {

    public static ArrayList<Command> commands;
    boolean b;

    public static void addCommand(Command c) {
        commands.add(c);
    }

    public static ArrayList<Command> getClientCommands() {
        return commands;
    }

    public void callClientCommand(String input) {
        String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String command = split[0];
        String args = input.substring(command.length()).trim();
        b = false;
        commands.forEach(c -> {
            for (String s : c.getClientAlias()) {
                if (s.equalsIgnoreCase(command)) {
                    b = true;
                    try {
                        c.onClientCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    } catch (Exception e) {
                        if (!s.equalsIgnoreCase("friend")) {
                            Command.sendClientSideMessage(Formatting.RED + c.getClientSyntax());
                        }

                    }
                }
            }
        });
        if (!b) Command.sendClientSideMessage(Formatting.RED + "Unknown command!");
    }

    public CommandManager() {
        commands = new ArrayList<>();
        commands.add(new Set());
        commands.add(new Toggle());
    }

}
