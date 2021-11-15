package coda.effishiency;

import coda.effishiency.init.FishEnchantments;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
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

        forgeBus.addListener(this::onFishCatch);
        FishEnchantments.ENCHATNMENTS.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffishiencyConfig.Common.SPEC);
    }

    private void onFishCatch(ItemFishedEvent event) {
        NonNullList<ItemStack> stacks = event.getDrops();
        InteractionHand hand = event.getPlayer().getUsedItemHand();
        ItemStack stack = event.getPlayer().getItemInHand(hand);
        if (EnchantmentHelper.getEnchantments(event.getPlayer().getItemInHand(hand)).containsKey(FishEnchantments.EFFISHIENCY.get())) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(FishEnchantments.EFFISHIENCY.get(), event.getPlayer().getItemInHand(hand));
            if (stacks.iterator().next().is(ItemTags.FISHES)) {
                int amount = event.getPlayer().getRandom().nextInt(i) + 2;
                int damageAmount = stack.getDamageValue() + amount - 1;
                stacks.iterator().next().setCount(amount);
                if (!EffishiencyConfig.Common.INSTANCE.durability.get()) {
                    stack.getItem().setDamage(stack, stack.getDamageValue());
                }
                else {
                    stack.getItem().setDamage(stack, damageAmount);
                }
            }
        }
        else {
            stacks.iterator().next().setCount(1);
            int damageAmount = stack.getDamageValue() + 1;
            stack.getItem().setDamage(stack, damageAmount);
        }
    }
}
