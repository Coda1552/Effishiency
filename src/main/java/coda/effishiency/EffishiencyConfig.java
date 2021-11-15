package coda.effishiency;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Effishiency.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EffishiencyConfig {
    public static boolean durability;

    @SubscribeEvent
    public static void configLoad(ModConfigEvent.Reloading event) {
        try {
            IConfigSpec spec = event.getConfig().getSpec();
            if (spec == Common.SPEC) Common.reload();
        }
        catch (Throwable e) {
            Effishiency.LOGGER.error("Something went wrong updating the Fins and Tails config, using previous or default values! {}", e.toString());
        }
    }

    public static class Common {
        public static final Common INSTANCE;
        public static final ForgeConfigSpec SPEC;

        static {
            Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
            INSTANCE = pair.getLeft();
            SPEC = pair.getRight();
        }

        public final ForgeConfigSpec.BooleanValue durability;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            durability = builder.comment("Should amount of durability damage depend on the amount of fish caught? If false, only one durability will be removed, regardless of amount of fish caught.").define("amount_of_durability", true);
            builder.pop();
        }

        public static void reload() {
            EffishiencyConfig.durability = INSTANCE.durability.get();
        }
    }
}