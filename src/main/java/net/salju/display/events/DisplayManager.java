package net.salju.display.events;

import net.salju.display.init.DisplayTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class DisplayManager {
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1).add(Attributes.ATTACK_DAMAGE, 0).add(Attributes.ARMOR, 0).add(Attributes.MOVEMENT_SPEED, 0.0).add(Attributes.FOLLOW_RANGE, 0).add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    public static SoundEvent getCustomTemplateSound(ItemStack stack, boolean check) {
        if (stack.is(DisplayTags.CALCITE)) {
            return check ? SoundEvents.CALCITE_PLACE : SoundEvents.CALCITE_BREAK;
        } else if (stack.is(DisplayTags.COPPER)) {
            return check ? SoundEvents.COPPER_PLACE : SoundEvents.COPPER_BREAK;
        } else if (stack.is(DisplayTags.DEEPSLATE)) {
            return check ? SoundEvents.DEEPSLATE_PLACE : SoundEvents.DEEPSLATE_BREAK;
        } else if (stack.is(DisplayTags.GRAVEL)) {
            return check ? SoundEvents.GRAVEL_PLACE : SoundEvents.GRAVEL_BREAK;
        } else if (stack.is(DisplayTags.NETHERBRICK)) {
            return check ? SoundEvents.NETHER_BRICKS_PLACE : SoundEvents.NETHER_BRICKS_BREAK;
        } else if (stack.is(DisplayTags.NETHERRACK)) {
            return check ? SoundEvents.NETHERRACK_PLACE : SoundEvents.NETHERRACK_BREAK;
        }
        return check ? SoundEvents.TUFF_PLACE : SoundEvents.TUFF_BREAK;
    }
}