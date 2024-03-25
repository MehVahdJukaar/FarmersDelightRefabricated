package vectorwing.farmersdelight.integration.crafttweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;

@Document("mods/FarmersDelight/ToolActionIngredient")
@ZenRegister
@ZenCodeType.Name("mods.farmersdelight.ToolActionIngredient")
public class CTToolActionIngredient implements IIngredient {

    public static final String PREFIX = "toolingredient";
    private final ToolActionIngredient ingredient;

    public CTToolActionIngredient(ToolAction toolAction) {
        this(new ToolActionIngredient(toolAction));
    }

    public CTToolActionIngredient(ToolActionIngredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(IItemStack stack, boolean ignoreDamage) {
        return ingredient.test(stack.getInternal());
    }

    @Override
    public Ingredient asVanillaIngredient() {
        return ingredient.toVanilla();
    }

    @Override
    public String getCommandString() {
        return "<" + PREFIX + ":" + ingredient.toolAction.name() + ">";
    }

    @Override
    public IItemStack[] getItems() {
        ItemStack[] stacks = ingredient.toVanilla().getItems();
        IItemStack[] out = new IItemStack[stacks.length];
        for (int i = 0; i < stacks.length; i++) {
            out[i] = new MCItemStackMutable(stacks[i]);
        }
        return out;
    }


    // Originally an extension to CraftTweaker's ToolAction, but that doesn't exist
    // on Fabric, so this is now a new NativeType.
    @ZenRegister
    @NativeTypeRegistration(value = ToolAction.class, zenCodeName = "farmersdelight.api.tool.ToolAction")
    public static class ExpandToolAction {
        @ZenCodeType.Method
        @ZenCodeType.Getter("name")
        public static String name(ToolAction internal) {

            return internal.name();
        }

        @ZenCodeType.Method
        @ZenCodeType.Getter("commandString")
        public static String getCommandString(ToolAction internal) {

            return "<toolaction:" + name(internal) + ">";
        }
        
        // Support the syntax:
        // <tooltype:axe> as IIngredient
        @ZenCodeType.Method
        @ZenCodeType.Caster(implicit = true)
        public static IIngredient asIIngredient(ToolAction internal) {
            return new CTToolActionIngredient(internal);
        }

    }
}
