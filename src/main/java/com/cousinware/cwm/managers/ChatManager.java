package com.cousinware.cwm.managers;

import com.cousinware.cwm.Cwm;
import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import net.minecraft.network.message.SignedMessage;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ChatManager {


    public void doChat(SignedMessage signedMessage, CallbackInfo info) {
        String message = signedMessage.getContent().getString();
        if (message.startsWith(Command.prefix)) {
            CwmClient.commandManager.callClientCommand(message.substring(1));

            info.cancel();

        }

    }


}


