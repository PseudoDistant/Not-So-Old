package net.legacyfabric.notsoold.mixin;

import com.google.common.base.Predicate;
import com.mojang.patchy.BlockedServers;
import com.mojang.patchy.BlockingICFB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.naming.spi.NamingManager;

@Mixin(BlockingICFB.class)
public class BlockingICFBMixin {
	/**
	 * @author PseudoDistant
	 */
	@Overwrite(remap = false)
	public static void install() {
		try {
			System.getProperties().setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
			NamingManager.setInitialContextFactoryBuilder(new BlockingICFB(new Predicate<String>() {
				public boolean apply(String input) {
					return false;
				}
			}));
		} catch (Throwable var1) {
			System.out.println("Block failed :(");
			var1.printStackTrace();
		}
	}
}
