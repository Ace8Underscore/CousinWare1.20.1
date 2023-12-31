package com.cousinware.cwm.command.commands;

import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.managers.HackManager;
import net.minecraft.util.Formatting;

public class Toggle extends Command {

    boolean found;

    @Override
    public String[] getClientAlias() {
        return new String[]{"toggle"};
    }

    @Override
    public String getClientSyntax() {
        return "Toggle (Hack)";
    }

    @Override
    public void onClientCommand(String command, String[] args) {
        found = false;
        HackManager.getHacks().forEach(m -> {
            if (m.getName().equalsIgnoreCase(args[0])) {
                if (m.isEnabled()) {
                    m.disable();
                    Command.sendClientSideMessage(args[0] + " Was" + Formatting.DARK_RED + " Disabled!");
                    found = true;
                } else if (!m.isEnabled()) {
                    m.enable();
                    found = true;
                    Command.sendClientSideMessage(args[0] + " Was" + Formatting.GREEN + " Enabled!");
                }
            }
        });
        if (!found && args.length == 1) Command.sendClientSideMessage(Formatting.DARK_RED + "Hack not found!");

    }
}



    /*    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        String toggleHack = args.toString();
        HackManager.getHackByName(toggleHack).toggle();

    }
} */