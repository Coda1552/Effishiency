package coda.effishiency;

import coda.effishiency.init.FishEnchantments;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
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
        Player player = event.getPlayer();
        InteractionHand hand = player.getUsedItemHand();
        ItemStack stack = player.getItemInHand(hand);
        if (EnchantmentHelper.getEnchantments(player.getItemInHand(hand)).containsKey(FishEnchantments.EFFISHIENCY.get())) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(FishEnchantments.EFFISHIENCY.get(), player.getItemInHand(hand));
            int amount = player.getRandom().nextInt(i) + 2;
            int damageAmount = stack.getDamageValue() + amount - 1;

            if (stacks.iterator().hasNext()) {
                ItemStack loot = stacks.iterator().next();

                ItemStack excess = loot.copy();

                ItemEntity item = EntityType.ITEM.create(player.level);
                item.setItem(excess);
                item.setPos(event.getHookEntity().position());

                FishingHook hook = event.getHookEntity();

                double d0 = player.getX() - hook.getX();
                double d1 = player.getY() - hook.getY();
                double d2 = player.getZ() - hook.getZ();
                item.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);

                for (int j = 0; j < amount; j++) {
                    player.level.addFreshEntity(item);
                }
            }

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
    }
}
