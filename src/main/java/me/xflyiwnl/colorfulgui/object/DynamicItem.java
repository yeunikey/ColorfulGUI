
package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.builder.item.DynamicItemBuilder;
import me.xflyiwnl.colorfulgui.object.action.UpdateItem;
import me.xflyiwnl.colorfulgui.object.action.click.ClickDynamicAction;
import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DynamicItem extends GuiItem {

    // runs this code when GUI updated
    private UpdateItem<UpdateItemEvent<DynamicItem>> onUpdate;
    // runs this code when item clicked
    private ClickDynamicAction action;

    public DynamicItem() {
    }

    /**
     * Constructor for a DynamicItem.
     *
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     * @param onUpdate  The action to be performed when the GUI is updated
     **/
    public DynamicItem(ItemStack itemStack, ClickDynamicAction action, UpdateItem<UpdateItemEvent<DynamicItem>> onUpdate) {
        super(itemStack, action);
        this.onUpdate = onUpdate;
        this.action = action;
    }

    /**
     * Constructor for a DynamicItem with a specified unique ID.
     *
     * @param uniqueId  The unique ID for the item
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     * @param onUpdate  The action to be performed when the GUI is updated
     **/
    public DynamicItem(UUID uniqueId, ItemStack itemStack, ClickDynamicAction action, UpdateItem<UpdateItemEvent<DynamicItem>> onUpdate) {
        super(uniqueId, itemStack, action);
        this.onUpdate = onUpdate;
        this.action = action;
    }

    /**
     * Retrieves a builder for creating {@link DynamicItemBuilder}.
     *
     * @return creates and return {@link DynamicItemBuilder}
     **/
    @Override
    public DynamicItemBuilder builder() {
        DynamicItemBuilder builder = new DynamicItemBuilder();
        builder.from(this);
        return builder;
    }

    /**
     * Retrieves the update action.
     *
     * @return The action to be performed when the GUI is updated
     **/
    public UpdateItem<UpdateItemEvent<DynamicItem>> getOnUpdate() {
        return onUpdate;
    }

    /**
     * Sets the update action.
     *
     * @param onUpdate The action to be set
     **/
    public void setOnUpdate(UpdateItem<UpdateItemEvent<DynamicItem>> onUpdate) {
        this.onUpdate = onUpdate;
    }

    /**
     * Retrieves the click action.
     *
     * @return The click action {@link ClickDynamicAction}
     **/
    @Override
    public ClickDynamicAction getAction() {
        return action;
    }

}