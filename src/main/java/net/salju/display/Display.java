package net.salju.display;

import net.salju.display.init.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

@Mod("items_displayed")
public class Display {
	public static final String MODID = "items_displayed";

	public Display(IEventBus bus) {
		DisplayMobs.REGISTRY.register(bus);
		DisplayItems.REGISTRY.register(bus);
		DisplayTabs.REGISTRY.register(bus);
	}
}