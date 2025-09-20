package com.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DelayedSoundTaskHandler {
    private static final List<Task> tasks = new ArrayList<>();

    private static boolean playingSound = false;

    //creates a task to play sound for fishing after a certain delay
    public static void scheduleFishingMusic(ServerWorld world, PlayerEntity player) {
        tasks.add(new Task(world, player.getUuid(), 100));
    }

    //this method is called every in game tick by registering it in the mod initializer
    public static void ticks(ServerWorld world) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            PlayerEntity player = world.getPlayerByUuid(t.playerUuid);

            if (t.world != world || player == null) {
                it.remove();
                continue;
            }

            boolean isFishing = player.fishHook != null && player.fishHook.isTouchingWater();

            if (isFishing) {
                if (!playingSound) {
                    // Start playing instantly
                    world.playSound(
                            null,
                            player.getBlockPos(),
                            MusicMod.MY_SOUND_EVENT,
                            SoundCategory.MUSIC,
                            0.3F, // fixed volume
                            1.0F
                    );
                    playingSound = true;
                }
            } else {
                if (playingSound) {
                    // Stop instantly
                    // There's no direct "stopSound" on ServerWorld, so you need to send
                    // a stop packet to the player. For example:
                    /*
                    player.networkHandler.sendPacket(
                            new StopSoundS2CPacket(MusicMod.MY_SOUND_EVENT.getId(), SoundCategory.MUSIC)
                    );
                    im a dumbass, do this clientside
                     */
                    playingSound = false;
                }
            }
        }
    }



    private static class Task {
        final ServerWorld world;
        final UUID playerUuid;
        int ticks;
        Task(ServerWorld world, UUID playerUuid, int ticks) {
            this.world = world; this.playerUuid = playerUuid; this.ticks = ticks;
        }
    }
}
