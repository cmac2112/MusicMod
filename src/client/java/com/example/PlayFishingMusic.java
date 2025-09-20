package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class PlayFishingMusic {
    //need a delay because the bobber floats on the water
    // if the delay hits 0 then stop music

    private static float maxVol = 0.4F;
    private static float minVol = 0.001F;
    private static FishingMusicSoundInstance currentMusic = null;
    public static void HandleFishingMusicPlay(MinecraftClient client, ClientPlayerEntity player) {
        if (player == null) return;
        boolean isFishing = player.fishHook != null && player.fishHook.isInOpenWater();

        //MusicMod.LOGGER.info("isFishing={}", isFishing);
        //MusicMod.LOGGER.info("currentMusic={}", currentMusic);
        if (isFishing) {
            if (currentMusic == null) {
                currentMusic = new FishingMusicSoundInstance();
                currentMusic.setVolume(minVol);

                client.getSoundManager().play(currentMusic);
                MusicMod.LOGGER.info("Fishing music created & play() called (vol={})", currentMusic.getVolume());

            }else{
               float volume = currentMusic.getVolume();
                MusicMod.LOGGER.info("currentMusic exists (vol={})", volume);
                if(volume < maxVol){
                    currentMusic.setVolume(volume + 0.005F);
                    MusicMod.LOGGER.info("Fading in music -> vol={}", volume);


                }
            }
        } else {
            if (currentMusic != null) {
                float volume = currentMusic.getVolume();
                if(volume > minVol){
                    currentMusic.setVolume(volume - 0.005F);
                    MusicMod.LOGGER.info("Fading out music -> vol={}", volume);
                    return;
                }
                client.getSoundManager().stop(currentMusic);
                currentMusic = null;
            }
        }
    }
}
