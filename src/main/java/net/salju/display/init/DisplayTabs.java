package net.salju.display.init;

import net.salju.display.Display;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class DisplayTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Display.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DISPLAYED = REGISTRY.register("display",
			() -> CreativeModeTab.builder().title(Component.translatable("item.items_displayed.item_group_name")).icon(() -> new ItemStack(DisplayItems.DISPLAY.get())).displayItems((parameters, tabData) -> {
				tabData.accept(DisplayItems.DISPLAY.get());
				tabData.accept(DisplayItems.WHITE_PILLOW.get());
				tabData.accept(DisplayItems.LG_PILLOW.get());
				tabData.accept(DisplayItems.GRAY_PILLOW.get());
				tabData.accept(DisplayItems.BLACK_PILLOW.get());
				tabData.accept(DisplayItems.RED_PILLOW.get());
				tabData.accept(DisplayItems.ORANGE_PILLOW.get());
				tabData.accept(DisplayItems.YELLOW_PILLOW.get());
				tabData.accept(DisplayItems.LIME_PILLOW.get());
				tabData.accept(DisplayItems.GREEN_PILLOW.get());
				tabData.accept(DisplayItems.LB_PILLOW.get());
				tabData.accept(DisplayItems.CYAN_PILLOW.get());
				tabData.accept(DisplayItems.BLUE_PILLOW.get());
				tabData.accept(DisplayItems.PURPLE_PILLOW.get());
				tabData.accept(DisplayItems.MAGE_PILLOW.get());
				tabData.accept(DisplayItems.PINK_PILLOW.get());
				tabData.accept(DisplayItems.BROWN_PILLOW.get());
			}).build());
}