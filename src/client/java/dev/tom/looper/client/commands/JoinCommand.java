package dev.tom.looper.client.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class JoinCommand {

    public JoinCommand(){

    }

    public LiteralArgumentBuilder<FabricClientCommandSource> getCommand() {
        return ClientCommandManager.literal("join")
                .executes(context -> {
                    ServerInfo info = new ServerInfo("OctanePvP", "play.octanepvp.com", ServerInfo.ServerType.OTHER);
                    MinecraftClient mc = MinecraftClient.getInstance();
                    mc.disconnect();
                    ConnectScreen.connect(null, MinecraftClient.getInstance(), ServerAddress.parse("play.octanepvp.com"), info, false, null);
                    return 1;
                });
    }
}
