package net.Poluteclient.phosphor.mixin;

import net.Poluteclient.phosphor.api.event.events.EntityMoveEvent;
import net.Poluteclient.phosphor.common.Phosphor;
import net.Poluteclient.phosphor.gui.AsteriaMenu;
import net.Poluteclient.phosphor.module.modules.combat.HitboxesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(Entity.class)
public class EntityMixin {
    @Inject(
            method = "getTargetingMargin",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetTargetingMargin(CallbackInfoReturnable<Float> cir) {
        HitboxesModule hitboxes = Phosphor.moduleManager().getModule(HitboxesModule.class);
        if (AsteriaMenu.isClientEnabled() && hitboxes.isEnabled()) {
            cir.setReturnValue(hitboxes.getHitboxSize((Entity) (Object) this));
        }
    }

    @Inject(method = "move", at = @At("HEAD"))
    private void onEntityMove(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        Phosphor.EVENTBUS.post(EntityMoveEvent.get((Entity) (Object) this));
    }
}
