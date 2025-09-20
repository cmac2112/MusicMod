package com.example;

import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.random.Random;


public class FishingMusicSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {

    private float dynamicVolume;
    public FishingMusicSoundInstance() {
        super(
                MusicMod.MY_SOUND_EVENT,
                SoundCategory.MUSIC,
                Random.create()
        );
        this.repeat = true;
        this.repeatDelay = 0;
        this.dynamicVolume = 0.001F; // Start at min volume
        this.pitch = 1.0F;
    }

    public void setVolume(float volume) {
        this.dynamicVolume = volume;
    }

    @Override
    public float getVolume() {
        return this.dynamicVolume;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void tick() {
        // Optionally, update state here if needed
    }
}