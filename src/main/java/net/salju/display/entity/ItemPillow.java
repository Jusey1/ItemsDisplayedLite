package net.salju.display.entity;

import net.salju.display.init.DisplayItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import java.util.Map;
import java.util.HashMap;

public class ItemPillow extends AbstractDisplayEntity {
	public static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(ItemPillow.class, EntityDataSerializers.INT);

	public ItemPillow(EntityType<ItemPillow> type, Level world) {
		super(type, world);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("PC", this.getColorInt());
	}

	@Override
	public void readAdditionalSaveData(ValueInput tag) {
		super.readAdditionalSaveData(tag);
		if (tag.getInt("PC").isPresent()) {
			this.setColorInt(tag.getInt("PC").get());
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(COLOR, 0);
	}

	@Override
	public ItemStack getDisplayItem() {
		return new ItemStack(this.getItemMap().getOrDefault(this.getColorInt(), DisplayItems.DISPLAY.get()));
	}

	public String getColor() {
		return this.getColorMap().getOrDefault(this.getColorInt(), "red");
	}

	public int getColorInt() {
		return this.getEntityData().get(COLOR);
	}

	public void setColorInt(int i) {
		this.getEntityData().set(COLOR, i);
	}

	public Map<Integer, String> getColorMap() {
		Map<Integer, String> cMap = new HashMap<>();
		cMap.put(0, "red");
		cMap.put(1, "white");
		cMap.put(2, "light_gray");
		cMap.put(3, "gray");
		cMap.put(4, "black");
		cMap.put(5, "orange");
		cMap.put(6, "yellow");
		cMap.put(7, "lime");
		cMap.put(8, "green");
		cMap.put(9, "light_blue");
		cMap.put(10, "cyan");
		cMap.put(11, "blue");
		cMap.put(12, "purple");
		cMap.put(13, "magenta");
		cMap.put(14, "pink");
		cMap.put(15, "brown");
		return cMap;
	}

	public Map<Integer, Item> getItemMap() {
		Map<Integer, Item> cMap = new HashMap<>();
		cMap.put(0, DisplayItems.RED_PILLOW.get());
		cMap.put(1, DisplayItems.WHITE_PILLOW.get());
		cMap.put(2, DisplayItems.LG_PILLOW.get());
		cMap.put(3, DisplayItems.GRAY_PILLOW.get());
		cMap.put(4, DisplayItems.BLACK_PILLOW.get());
		cMap.put(5, DisplayItems.ORANGE_PILLOW.get());
		cMap.put(6, DisplayItems.YELLOW_PILLOW.get());
		cMap.put(7, DisplayItems.LIME_PILLOW.get());
		cMap.put(8, DisplayItems.GREEN_PILLOW.get());
		cMap.put(9, DisplayItems.LB_PILLOW.get());
		cMap.put(10, DisplayItems.CYAN_PILLOW.get());
		cMap.put(11, DisplayItems.BLUE_PILLOW.get());
		cMap.put(12, DisplayItems.PURPLE_PILLOW.get());
		cMap.put(13, DisplayItems.MAGE_PILLOW.get());
		cMap.put(14, DisplayItems.PINK_PILLOW.get());
		cMap.put(15, DisplayItems.BROWN_PILLOW.get());
		return cMap;
	}
}