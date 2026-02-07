package net.salju.display.client.renderer;

import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.world.item.ItemStack;

public class AbstractDisplayState extends HoldingEntityRenderState {
	public ItemStack stack;
	public String getType;
	public float lastHit;
	public float yRot;

	public ItemStack getItemStack() {
		return stack;
	}
}