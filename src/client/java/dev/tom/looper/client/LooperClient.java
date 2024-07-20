package dev.tom.looper.client;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class LooperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("loop")
                    .then(CommandManager.argument("number", IntegerArgumentType.integer()))
                            .executes(context -> {
                                String command = StringArgumentType.getString(context, "command");
                                System.out.println(command);
                                context.getSource().getServer().getCommandManager().getDispatcher().execute(command, context.getSource());
                                return 1;
                })
                    .then(CommandManager.argument("command", StringArgumentType.string())
                            .executes(context -> {
                                System.out.println("here");
                                int number = IntegerArgumentType.getInteger(context, "number");
                                String command = StringArgumentType.getString(context, "command");
                                for (int i = 0; i < number; i++) {
                                    context.getSource().getServer().getCommandManager().getDispatcher().execute(command, context.getSource());
                                }
                                return 1;
                            }
            )));
        });
    }
}
