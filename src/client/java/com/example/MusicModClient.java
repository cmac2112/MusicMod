package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static com.example.MusicMod.LOGGER;

public class MusicModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        LOGGER.info("Hello Fabric world!");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayFishingMusic.HandleFishingMusicPlay(client, client.player);
        });
	}
}