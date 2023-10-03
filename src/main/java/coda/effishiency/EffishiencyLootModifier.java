package coda.effishiency;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class EffishiencyLootModifier extends LootModifier {

    public EffishiencyLootModifier(LootItemCondition[] condition) {
        super(condition);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> ret = new ObjectArrayList<>();
        ItemStack stack = context.getParamOrNull(LootContextParams.TOOL);

        if (stack != null) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(EffishiencyRegistry.EFFISHIENCY.get(), stack);

            if (stack.is(Tags.Items.TOOLS_FISHING_RODS) && i > 0) {
                int amount = new Random().nextInt(i) + 2;
                int damageAmount = stack.getDamageValue() + amount - 1;

                for (int j = 0; j < amount; j++) {
                    ret.add(generatedLoot.get(0).copy());
                }

                if (!EffishiencyConfig.Common.INSTANCE.durability.get()) {
                    stack.getItem().setDamage(stack, stack.getDamageValue());
                } else {
                    stack.getItem().setDamage(stack, damageAmount);
                }
            }
            else {
                ret = generatedLoot;
            }
        }
        return ret;
    }

    public static final Supplier<Codec<EffishiencyLootModifier>> CODEC = () -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, EffishiencyLootModifier::new));

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}