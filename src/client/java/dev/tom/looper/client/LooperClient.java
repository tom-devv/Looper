package dev.tom.looper.client;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;


public class LooperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("loop")
                    .then(ClientCommandManager.argument("number", IntegerArgumentType.integer())
                            .then(ClientCommandManager.argument("command", StringArgumentType.greedyString())
                                    .executes(context -> {
                                        context.getSource().getClient().player.networkHandler.sendChatCommand(StringArgumentType.getString(context, "command"));
                                        int count = IntegerArgumentType.getInteger(context, "number");
                                        String command = StringArgumentType.getString(context, "command");
                                        for (int i = 0; i < count; i++) {
                                            context.getSource().getClient().player.networkHandler.sendChatCommand(command);
                                        }
                                        return 1;
                                    })
                            )
                    )
            );

        });
//        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
//            dispatcher.register(ClientCommandManager.literal("loop")
//                    .then(CommandManager.argument("number", IntegerArgumentType.integer())
//                            .then(CommandManager.argument("command", StringArgumentType.greedyString())
//                                    .executes(context -> {
//                                        int count = IntegerArgumentType.getInteger(context, "number");
//                                        String command = StringArgumentType.getString(context, "command");
//                                        CommandDispatcher<ServerCommandSource> commandDispatcher = context.getSource().getServer().getCommandManager().getDispatcher();
//                                        for (int i = 0; i < count; i++) {
//                                            commandDispatcher.execute(command, context.getSource());
//                                        }
//                                        return 1;
//                                    })
//                            )
//                    )
//            );
//        });
    }
}
