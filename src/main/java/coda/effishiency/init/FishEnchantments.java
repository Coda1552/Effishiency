package coda.effishiency.init;

import coda.effishiency.Effishiency;
import coda.effishiency.enchantments.EffishiencyEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FishEnchantments {
    public static final DeferredRegister<Enchantment> ENCHATNMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Effishiency.MOD_ID);

    public static final RegistryObject<EffishiencyEnchantment> EFFISHIENCY = ENCHATNMENTS.register("effishiency", () -> new EffishiencyEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
}
