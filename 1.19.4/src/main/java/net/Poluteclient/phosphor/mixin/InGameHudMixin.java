package net.Poluteclient.phosphor.mixin;

import net.Poluteclient.phosphor.common.Phosphor;
import net.Poluteclient.phosphor.api.event.events.HudRenderEvent;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        Phosphor.EVENTBUS.post(HudRenderEvent.get(matrices, tickDelta));
    }
}
