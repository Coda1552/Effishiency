package coda.effishiency.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

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

    @Override
    protected boolean checkCompatibility(Enchantment p_44690_) {
        return super.checkCompatibility(p_44690_) && p_44690_ != Enchantments.FISHING_LUCK;
    }

}
