package coda.effishiency;

import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

        //forgeBus.addListener(EventPriority.HIGHEST, this::onFishCatch);
        EffishiencyRegistry.ENCHANTMENTS.register(bus);
        EffishiencyRegistry.GLM.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffishiencyConfig.Common.SPEC);
    }

    /*private void onFishCatch(ItemFishedEvent event) {
        NonNullList<ItemStack> stacks = event.getDrops();
        InteractionHand hand = event.getEntity().getUsedItemHand();
        ItemStack stack = event.getEntity().getItemInHand(hand);
        if (EnchantmentHelper.getEnchantments(event.getEntity().getItemInHand(hand)).containsKey(FishEnchantments.EFFISHIENCY.get())) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(FishEnchantments.EFFISHIENCY.get(), event.getEntity().getItemInHand(hand));
            int amount = event.getEntity().getRandom().nextInt(i) + 2;
            int damageAmount = stack.getDamageValue() + amount - 1;

            stacks.iterator().next().setCount(amount);

            if (!EffishiencyConfig.Common.INSTANCE.durability.get()) {
                stack.getItem().setDamage(stack, stack.getDamageValue());
            }
            else {
                stack.getItem().setDamage(stack, damageAmount);
            }
        }
        else {
            stacks.iterator().next().setCount(1);
            int damageAmount = stack.getDamageValue() + 1;
            stack.getItem().setDamage(stack, damageAmount);
        }
    }*/
}
