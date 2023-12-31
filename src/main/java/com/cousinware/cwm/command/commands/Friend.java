package com.cousinware.cwm.command.commands;

import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.managers.FriendManager;
import net.minecraft.util.Formatting;

public class Friend extends Command {

    /**
     * @author Ace________/Ace_#1233
     */

    @Override
    public String[] getClientAlias() {
        return new String[]{"Friend", "Friends"};
    }

    @Override
    public String getClientSyntax() {
        return "";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        if (args[0].equals("add") && !FriendManager.isFriend(args[1])) {
            FriendManager.addFriend(args[1]);
            Command.sendClientSideMessage(Formatting.GREEN + args[1] + Formatting.WHITE + " Has Been Added To The Friends List ");
        }


        if (args[0].equals("del")) {
            FriendManager.getFriends()
                    .stream()
                    .forEach(friend -> {
                        if (friend.getName().contains(args[1])) {
                            FriendManager.removeFriend(args[1]);
                            Command.sendClientSideMessage(Formatting.DARK_RED + args[1] + " Has Been UnFriended");

                        }
                    });

        }
        if (!args[0].equals("add") && !args[0].equals("del")) {
            if (!FriendManager.isFriend(args[0])) {
                FriendManager.addFriend(args[0]);
                Command.sendClientSideMessage(Formatting.GREEN + args[0] + Formatting.WHITE + " Has Been Added To The Friends List ");

            } else {
                FriendManager.getFriends()
                        .stream()
                        .forEach(friend -> {
                            if (friend.getName().contains(args[0])) {
                                FriendManager.removeFriend(args[0]);
                                Command.sendClientSideMessage(Formatting.DARK_RED + args[0] + " Has Been UnFriended");

                            }
                        });
            }
        }
//    }
    }
}