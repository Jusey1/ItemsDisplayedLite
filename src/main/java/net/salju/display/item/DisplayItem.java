package net.salju.display.item;

import net.salju.display.init.DisplayMobs;
import net.salju.display.entity.AbstractDisplayEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class DisplayItem extends AbstractEntityItem {
	public DisplayItem(Item.Properties props) {
		super(props);
	}

	@Override
	public EntityType<? extends AbstractDisplayEntity> getType() {
		return DisplayMobs.DISPLAY.get();
	}
}