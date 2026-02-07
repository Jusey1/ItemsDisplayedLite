package net.salju.display.init;

import net.salju.display.Display;
import net.salju.display.client.model.*;
import net.salju.display.client.renderer.*;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.resources.Identifier;
import net.minecraft.client.model.geom.ModelLayerLocation;

@EventBusSubscriber(value = Dist.CLIENT)
public class DisplayClient {
	public static final ModelLayerLocation DISPLAY = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Display.MODID, "display"), "main");
	public static final ModelLayerLocation PILLOW = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Display.MODID, "pillow"), "main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DISPLAY, ItemDisplayModel::createBodyLayer);
		event.registerLayerDefinition(PILLOW, PillowModel::createBodyLayer);
	}

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DisplayMobs.DISPLAY.get(), ItemDisplayRenderer::new);
        event.registerEntityRenderer(DisplayMobs.DISPLAY_PILLOW.get(), PillowRenderer::new);
    }
}