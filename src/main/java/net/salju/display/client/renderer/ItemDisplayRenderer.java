package net.salju.display.client.renderer;

import net.salju.display.Display;
import net.salju.display.init.DisplayClient;
import net.salju.display.entity.ItemDisplay;
import net.salju.display.client.model.ItemDisplayModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

public class ItemDisplayRenderer extends LivingEntityRenderer<ItemDisplay, AbstractDisplayState, ItemDisplayModel<AbstractDisplayState>> {
	public ItemDisplayRenderer(EntityRendererProvider.Context context) {
		super(context, new ItemDisplayModel(context.bakeLayer(DisplayClient.DISPLAY)), 0.0F);
		this.addLayer(new DisplayedItemLayer(this));
	}

	@Override
	public Identifier getTextureLocation(AbstractDisplayState target) {
		return Identifier.fromNamespaceAndPath(Display.MODID, "textures/entity/item_display.png");
	}

	@Override
	public AbstractDisplayState createRenderState() {
		return new AbstractDisplayState();
	}

	@Override
	public void extractRenderState(ItemDisplay display, AbstractDisplayState state, float f1) {
		super.extractRenderState(display, state, f1);
		this.itemModelResolver.updateForLiving(state.heldItem, display.getMainHandItem(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, display);
		state.yRot = Mth.rotLerp(f1, display.yRotO, display.getYRot());
		state.lastHit = (float) (display.level().getGameTime() - display.lastHit) + f1;
		state.stack = display.getMainHandItem();
	}

	@Override
	protected boolean shouldShowName(ItemDisplay target, double d) {
		return false;
	}

	@Override
	protected void setupRotations(AbstractDisplayState target, PoseStack pose, float f1, float f2) {
		pose.mulPose(Axis.YP.rotationDegrees(180.0F - f1));
		if (target.lastHit < 5.0F) {
			pose.mulPose(Axis.YP.rotationDegrees(Mth.sin(target.lastHit / 1.5F * (float) Math.PI) * 3.0F));
		}
	}
}