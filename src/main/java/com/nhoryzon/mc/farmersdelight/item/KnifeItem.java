package com.nhoryzon.mc.farmersdelight.item;

import net.minecraft.world.item.Tier;

// Dont use!
@Deprecated(forRemoval = true)
public class KnifeItem extends vectorwing.farmersdelight.common.item.KnifeItem {

    public KnifeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    public KnifeItem(Tier material) {
        super(material,.5f, -1.8f, new Properties());
    }

    public KnifeItem(Tier material, Properties settings) {
        super(material,.5f, -1.8f, settings);
    }

}
