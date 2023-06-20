package coda.effishiency;

import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Effishiency.MOD_ID)
@Mod.EventBusSubscriber(modid = Effishiency.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Effishiency {
    public static final String MOD_ID = "effishiency";
    public static final Logger LOGGER = LogManager.getLogger();

    public Effishiency() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        EffishiencyRegistry.ENCHANTMENTS.register(bus);
        EffishiencyRegistry.GLM.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffishiencyConfig.Common.SPEC);
    }
}
