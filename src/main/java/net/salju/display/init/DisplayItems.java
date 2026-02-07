package net.salju.display.init;

import net.salju.display.Display;
import net.salju.display.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

public class DisplayItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, Display.MODID);
	public static final DeferredHolder<Item, Item> DISPLAY = REGISTRY.register("item_display", () -> new DisplayItem(createBaseProps("item_display")));
	public static final DeferredHolder<Item, Item> WHITE_PILLOW = REGISTRY.register("white_display_pillow", () -> new PillowItem(createBaseProps("white_display_pillow")));
	public static final DeferredHolder<Item, Item> LG_PILLOW = REGISTRY.register("light_gray_display_pillow", () -> new PillowItem(createBaseProps("light_gray_display_pillow")));
	public static final DeferredHolder<Item, Item> GRAY_PILLOW = REGISTRY.register("gray_display_pillow", () -> new PillowItem(createBaseProps("gray_display_pillow")));
	public static final DeferredHolder<Item, Item> BLACK_PILLOW = REGISTRY.register("black_display_pillow", () -> new PillowItem(createBaseProps("black_display_pillow")));
	public static final DeferredHolder<Item, Item> RED_PILLOW = REGISTRY.register("red_display_pillow", () -> new PillowItem(createBaseProps("red_display_pillow")));
	public static final DeferredHolder<Item, Item> ORANGE_PILLOW = REGISTRY.register("orange_display_pillow", () -> new PillowItem(createBaseProps("orange_display_pillow")));
	public static final DeferredHolder<Item, Item> YELLOW_PILLOW = REGISTRY.register("yellow_display_pillow", () -> new PillowItem(createBaseProps("yellow_display_pillow")));
	public static final DeferredHolder<Item, Item> LIME_PILLOW = REGISTRY.register("lime_display_pillow", () -> new PillowItem(createBaseProps("lime_display_pillow")));
	public static final DeferredHolder<Item, Item> GREEN_PILLOW = REGISTRY.register("green_display_pillow", () -> new PillowItem(createBaseProps("green_display_pillow")));
	public static final DeferredHolder<Item, Item> LB_PILLOW = REGISTRY.register("light_blue_display_pillow", () -> new PillowItem(createBaseProps("light_blue_display_pillow")));
	public static final DeferredHolder<Item, Item> CYAN_PILLOW = REGISTRY.register("cyan_display_pillow", () -> new PillowItem(createBaseProps("cyan_display_pillow")));
	public static final DeferredHolder<Item, Item> BLUE_PILLOW = REGISTRY.register("blue_display_pillow", () -> new PillowItem(createBaseProps("blue_display_pillow")));
	public static final DeferredHolder<Item, Item> PURPLE_PILLOW = REGISTRY.register("purple_display_pillow", () -> new PillowItem(createBaseProps("purple_display_pillow")));
	public static final DeferredHolder<Item, Item> MAGE_PILLOW = REGISTRY.register("magenta_display_pillow", () -> new PillowItem(createBaseProps("magenta_display_pillow")));
	public static final DeferredHolder<Item, Item> PINK_PILLOW = REGISTRY.register("pink_display_pillow", () -> new PillowItem(createBaseProps("pink_display_pillow")));
	public static final DeferredHolder<Item, Item> BROWN_PILLOW = REGISTRY.register("brown_display_pillow", () -> new PillowItem(createBaseProps("brown_display_pillow")));

	public static Item.Properties createBaseProps(String name) {
		return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Display.MODID, name)));
	}
}