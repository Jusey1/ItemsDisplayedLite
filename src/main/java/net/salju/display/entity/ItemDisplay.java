package net.salju.display.entity;

import net.salju.display.init.DisplayItems;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;

public class ItemDisplay extends AbstractDisplayEntity {
	public ItemDisplay(EntityType<ItemDisplay> type, Level world) {
		super(type, world);
	}

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(DisplayItems.DISPLAY.get());
    }
}