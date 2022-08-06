package net.legacyfabric.notsoold.mixin;

import com.google.common.base.Predicate;
import com.mojang.patchy.BlockingDC;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.naming.directory.DirContext;

@Mixin(BlockingDC.class)
public class BlockingDCMixin {
	@Shadow @Final @Mutable private Predicate<String> blockList;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void noBlockList(Predicate blockList, DirContext parent, CallbackInfo ci) {
		this.blockList = null;
	}
}
