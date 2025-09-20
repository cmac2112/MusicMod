package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicMod implements ModInitializer {
	public static final String MOD_ID = "musicmod";

    public static final Identifier MY_SOUND_ID = Identifier.of(MOD_ID, "lake");
    public static SoundEvent MY_SOUND_EVENT = SoundEvent.of(MY_SOUND_ID);

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
        Registry.register(Registries.SOUND_EVENT, MY_SOUND_ID, MY_SOUND_EVENT);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("playlake")
                    .executes(context -> {
                        ServerPlayerEntity player = context.getSource().getPlayer();

                        if (player != null) {
                            System.out.println("Playing sound for player: " + player.getName().getString());
                            player.getWorld().playSound(
                                    null,
                                    player.getBlockPos(),
                                    MY_SOUND_EVENT,
                                    SoundCategory.MUSIC,
                                    0.5F,
                                    1.0F
                            );
                            return 1;
                        }
                        return 0;
                    })
            );
        });
	}
}