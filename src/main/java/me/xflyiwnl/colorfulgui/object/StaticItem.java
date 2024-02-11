package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.builder.item.StaticItemBuilder;
import me.xflyiwnl.colorfulgui.object.action.click.ClickStaticAction;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class StaticItem extends GuiItem {

    // runs this code, when click
    private ClickStaticAction action;

    public StaticItem() {
    }

    /**
     * Constructor for a StaticItem.
     *
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     **/
    public StaticItem(ItemStack itemStack, ClickStaticAction action) {
        super(itemStack, action);
        this.action = action;
    }

    /**
     * Constructor for a StaticItem with a specified unique ID.
     *
     * @param uniqueId  The unique ID for the item
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     **/
    public StaticItem(UUID uniqueId, ItemStack itemStack, ClickStaticAction action) {
        super(uniqueId, itemStack, action);
        this.action = action;
    }

    /**
     * Retrieves a builder for creating {@link StaticItemBuilder}.
     *
     * @return A builder for creating {@link StaticItemBuilder}
     **/
    @Override
    public StaticItemBuilder builder() {
        StaticItemBuilder builder = new StaticItemBuilder();
        builder.from(this);
        return builder;
    }

    /**
     * Retrieves the {@link ClickStaticAction}.
     *
     * @return The click action {@link ClickStaticAction}
     **/
    @Override
    public ClickStaticAction getAction() {
        return action;
    }

}