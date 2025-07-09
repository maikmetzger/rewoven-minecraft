package com.gaukh.rewoven.mixin;

import com.gaukh.rewoven.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin {
    @Unique
    private boolean rewoven$hasDroppedShell = false;

    private static final Random RANDOM = new Random();
    private static final double DROP_CHANCE = 0.5;

    @Inject(
            method = "onCollision",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onCollisionMixin(HitResult hitResult, CallbackInfo ci) {
        if (rewoven$hasDroppedShell) {
            return; // Prevent multiple drops
        }
        rewoven$hasDroppedShell = true; // Set flag to prevent further drops

        EggEntity egg = (EggEntity) (Object) this; // cast to target
        World world = egg.getWorld();
        if (!world.isClient) {
            if (RANDOM.nextDouble() < DROP_CHANCE) {
                // Spawn your cracked egg shell at impact location
                ItemStack shell = new ItemStack(ModItems.CRACKED_EGG_SHELL);
                ItemEntity drop = new ItemEntity(
                        world,
                        egg.getX(),
                        egg.getY(),
                        egg.getZ(),
                        shell
                );
                world.spawnEntity(drop);
            }

            // Play sound effect for egg break
            world.playSound(
                    null,
                    egg.getX(),
                    egg.getY(),
                    egg.getZ(),
                    SoundEvents.ENTITY_TURTLE_EGG_BREAK,
                    net.minecraft.sound.SoundCategory.BLOCKS,
                    0.5f,
                    2.3f
            );
        }
    }
}