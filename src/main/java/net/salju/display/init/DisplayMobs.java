package net.salju.display.init;

import net.salju.display.Display;
import net.salju.display.entity.*;
import net.salju.display.events.DisplayManager;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

@EventBusSubscriber
public class DisplayMobs {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, Display.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<ItemDisplay>> DISPLAY = register("item_display", EntityType.Builder.of(ItemDisplay::new, MobCategory.MISC).sized(0.45F, 0.75F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ItemPillow>> DISPLAY_PILLOW = register("display_pillow", EntityType.Builder.of(ItemPillow::new, MobCategory.MISC).sized(0.56F, 0.45F).clientTrackingRange(10));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Display.MODID, registryname))));
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(DISPLAY.get(), DisplayManager.createAttributes().build());
		event.put(DISPLAY_PILLOW.get(), DisplayManager.createAttributes().build());
	}
}