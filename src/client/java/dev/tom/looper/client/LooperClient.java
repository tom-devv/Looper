package dev.tom.looper.client;


import dev.tom.looper.client.commands.JoinCommand;
import dev.tom.looper.client.commands.LoopCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;


public class LooperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(new LoopCommand().getCommand());
            dispatcher.register(new JoinCommand().getCommand());
        });
    }
}
