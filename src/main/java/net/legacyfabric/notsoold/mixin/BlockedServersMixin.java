package net.legacyfabric.notsoold.mixin;

import com.mojang.patchy.BlockedServers;
import org.apache.commons.io.IOUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Set;

@Mixin(BlockedServers.class)
public class BlockedServersMixin {
	@Shadow @Final private static Set<String> BLOCKED_SERVERS;

	@Shadow @Final private static Charset HASH_CHARSET;

	/** @author PseudoDistant */
	@Overwrite(remap = false)
	public static boolean isBlockedServer(String server) {
		return false;
	}
	@Inject(method = "<clinit>", at = @At(value = "TAIL"))
	private static void setListEmpty(CallbackInfo ci) {
		try {
			IOUtils.readLines((new URL("https://sessionserver.mojang.com/blockedservers")).openConnection().getInputStream(), HASH_CHARSET).forEach(BLOCKED_SERVERS::remove);
		} catch (IOException e) {}
	}
}
