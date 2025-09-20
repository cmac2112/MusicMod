package com.example.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class MixinFishingRodItem {
    @Inject(method = "use", at = @At("RETURN") )
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
            if (!world.isClient) {
                if(user.fishHook != null) {

                    //play sound
                   // DelayedSoundTaskHandler.scheduleFishingMusic((ServerWorld) world, user);
                }else{
                    //fade sound out
                }
            }

    }
}
