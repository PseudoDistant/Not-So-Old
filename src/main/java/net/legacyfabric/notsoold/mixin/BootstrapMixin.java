package net.legacyfabric.notsoold.mixin;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.SocketAddress;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
	/**
	 * @author PseudoDistant
	 */
	@Overwrite(remap = false)
	ChannelFuture checkAddress(SocketAddress remoteAddress) {
		return null;
	}

	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/patchy/BlockingICFB;install()V"))
	private static void noBlocking() {}
}
