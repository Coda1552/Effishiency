package coda.effishiency.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EffishiencyEnchantment extends Enchantment {

    public EffishiencyEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot[] slot) {
        super(rarity, type, slot);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}
