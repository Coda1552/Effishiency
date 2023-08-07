package coda.effishiency;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffishiencyRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Effishiency.MOD_ID);
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Effishiency.MOD_ID);

    public static final RegistryObject<EffishiencyEnchantment> EFFISHIENCY = ENCHANTMENTS.register("effishiency", () -> new EffishiencyEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> FISH_LOOT_MODIFIER = GLM.register("fish_glm", EffishiencyLootModifier.CODEC);
}