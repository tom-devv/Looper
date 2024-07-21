package dev.tom.looper.client.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class LoopCommand {

    public LoopCommand() {

    }

    public LiteralArgumentBuilder<FabricClientCommandSource> getCommand() {
        return ClientCommandManager.literal("loop")
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
            );
    }
}
