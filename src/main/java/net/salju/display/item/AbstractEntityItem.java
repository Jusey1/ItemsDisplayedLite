package net.salju.display.item;

import net.salju.display.init.DisplayItems;
import net.salju.display.entity.*;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.InteractionResult;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEntityItem extends Item {
	public AbstractEntityItem(Item.Properties props) {
		super(props);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.FAIL;
		} else {
			Level world = context.getLevel();
			BlockPlaceContext place = new BlockPlaceContext(context);
			BlockPos pos = place.getClickedPos();
			ItemStack stack = context.getItemInHand();
			Vec3 v = Vec3.atBottomCenterOf(pos);
			AABB nums = this.getType().getDimensions().makeBoundingBox(v.x(), v.y(), v.z());
			if (world.noCollision(null, nums) && world.getEntities(null, nums).isEmpty()) {
				if (world instanceof ServerLevel lvl) {
					AbstractDisplayEntity target = this.getType().create(lvl, EntityType.createDefaultStackConfig(lvl, stack, context.getPlayer()), pos, EntitySpawnReason.SPAWN_ITEM_USE, true, true);
					if (target == null) {
						return InteractionResult.FAIL;
					}
					if (target instanceof ItemPillow display) {
						display.setColorInt(this.getColorMap().getOrDefault(this, 0));
					}
					float r = 45.0F;
					float rot = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 22.5F) / r) * r;
					target.snapTo(target.getX(), target.getY(), target.getZ(), rot, 0.0F);
					lvl.addFreshEntityWithPassengers(target);
					world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.ARMOR_STAND_PLACE, SoundSource.BLOCKS, 0.75F, 0.8F);
					target.gameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
                    stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.FAIL;
			}
		}
	}

	public Map<Item, Integer> getColorMap() {
		Map<Item, Integer> cMap = new HashMap<>();
		cMap.put(DisplayItems.RED_PILLOW.get(), 0);
		cMap.put(DisplayItems.WHITE_PILLOW.get(), 1);
		cMap.put(DisplayItems.LG_PILLOW.get(), 2);
		cMap.put(DisplayItems.GRAY_PILLOW.get(), 3);
		cMap.put(DisplayItems.BLACK_PILLOW.get(), 4);
		cMap.put(DisplayItems.ORANGE_PILLOW.get(), 5);
		cMap.put(DisplayItems.YELLOW_PILLOW.get(), 6);
		cMap.put(DisplayItems.LIME_PILLOW.get(), 7);
		cMap.put(DisplayItems.GREEN_PILLOW.get(), 8);
		cMap.put(DisplayItems.LB_PILLOW.get(), 9);
		cMap.put(DisplayItems.CYAN_PILLOW.get(), 10);
		cMap.put(DisplayItems.BLUE_PILLOW.get(), 11);
		cMap.put(DisplayItems.PURPLE_PILLOW.get(), 12);
		cMap.put(DisplayItems.MAGE_PILLOW.get(), 13);
		cMap.put(DisplayItems.PINK_PILLOW.get(), 14);
		cMap.put(DisplayItems.BROWN_PILLOW.get(), 15);
		return cMap;
	}

	public abstract EntityType<? extends AbstractDisplayEntity> getType();
}