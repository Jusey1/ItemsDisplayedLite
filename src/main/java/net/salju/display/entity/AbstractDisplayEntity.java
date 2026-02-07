package net.salju.display.entity;

import net.salju.display.init.DisplayTags;
import net.salju.display.events.DisplayManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

public abstract class AbstractDisplayEntity extends LivingEntity {
	public long lastHit;

	public AbstractDisplayEntity(EntityType<? extends LivingEntity> type, Level world) {
		super(type, world);
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 v, InteractionHand hand) {
		if (this.level() instanceof ServerLevel lvl && hand.equals(InteractionHand.MAIN_HAND)) {
			ItemStack stack = player.getItemInHand(hand);
			ItemStack current = this.getMainHandItem();
			if (current.isEmpty()) {
				if (stack.is(DisplayTags.DISPLAYABLE)) {
					ItemStack copy = stack.copyWithCount(1);
                    lvl.playSound(null, this.blockPosition(), this.getPlaceSound(copy), SoundSource.BLOCKS, 1.0F, (float) (0.8F + (Math.random() * 0.2)));
					this.setItemInHand(InteractionHand.MAIN_HAND, copy);
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
					player.swing(hand, true);
					return InteractionResult.SUCCESS;
				}
			} else if (player.getMainHandItem().isEmpty()) {
                lvl.playSound(null, this.blockPosition(), this.getBreakSound(current), SoundSource.BLOCKS, 1.0F, (float) (0.8F + (Math.random() * 0.2)));
				player.addItem(current);
				current.shrink(1);
				player.swing(hand, true);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	public boolean hurtServer(ServerLevel lvl, DamageSource source, float amount) {
		if (!this.isRemoved()) {
			if (source.getEntity() != null) {
				if (source.isCreativePlayer() || source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || source.is(DamageTypeTags.IS_EXPLOSION)) {
					this.die(source);
				} else {
					long i = this.level().getGameTime();
					if (i - this.lastHit > 5L) {
						this.level().broadcastEntityEvent(this, (byte) 32);
						this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
						this.lastHit = i;
					} else {
						this.die(source);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		boolean flag = (source.getEntity() instanceof Player player && player.isCreative());
		this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_BREAK, this.getSoundSource(), 1.0F, 1.0F);
		this.showBreakingParticles();
		if (!flag) {
			ItemEntity target = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getDisplayItem());
			target.setPickUpDelay(10);
			this.level().addFreshEntity(target);
			if (!this.getMainHandItem().isEmpty()) {
				ItemEntity drop = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getMainHandItem());
				drop.setPickUpDelay(10);
				this.level().addFreshEntity(drop);
			}
		}
		this.discard();
	}

	@Override
	public void handleEntityEvent(byte b) {
		if (b == 32) {
			if (this.level().isClientSide()) {
				this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3F, 1.0F, false);
				this.lastHit = this.level().getGameTime();
			}
		} else {
			super.handleEntityEvent(b);
		}
	}

	@Override
	public SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ARMOR_STAND_HIT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.ARMOR_STAND_BREAK;
	}

	@Override
	public void setYBodyRot(float f1) {
		this.yBodyRotO = this.yRotO = f1;
		this.yHeadRotO = this.yHeadRot = f1;
	}

	@Override
	public void setYHeadRot(float f1) {
		this.yBodyRotO = this.yRotO = f1;
		this.yHeadRotO = this.yHeadRot = f1;
	}

	@Override
	protected void tickHeadTurn(float f1) {
		this.yBodyRotO = this.yRotO;
		this.yBodyRot = this.getYRot();
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity target) {
		//
	}

	@Override
	protected void pushEntities() {
		//
	}

	@Override
	public HumanoidArm getMainArm() {
		return HumanoidArm.RIGHT;
	}

    @Override
    public ItemStack getPickResult() {
        if (this.getMainHandItem().isEmpty()) {
            return this.getDisplayItem();
        }
        return this.getMainHandItem();
    }

    public abstract ItemStack getDisplayItem();

    public SoundEvent getPlaceSound(ItemStack stack) {
        if (stack.is(DisplayTags.SHERDS)) {
            return SoundEvents.DECORATED_POT_PLACE;
        } else if (stack.is(DisplayTags.TEMPLATES)) {
            return DisplayManager.getCustomTemplateSound(stack, true);
        }
        return SoundEvents.STONE_PLACE;
    }

    public SoundEvent getBreakSound(ItemStack stack) {
        if (stack.is(DisplayTags.SHERDS)) {
            return SoundEvents.DECORATED_POT_BREAK;
        } else if (stack.is(DisplayTags.TEMPLATES)) {
            return DisplayManager.getCustomTemplateSound(stack, false);
        }
        return SoundEvents.STONE_BREAK;
    }

	private void showBreakingParticles() {
		if (this.level() instanceof ServerLevel lvl) {
			lvl.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()), this.getX(), this.getY(0.35D), this.getZ(), 10, this.getBbWidth() / 4.0F, this.getBbHeight() / 4.0F, this.getBbWidth() / 4.0F, 0.05D);
		}
	}
}