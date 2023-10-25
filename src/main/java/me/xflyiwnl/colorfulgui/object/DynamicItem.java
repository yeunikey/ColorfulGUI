package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.object.action.GuiAction;
import me.xflyiwnl.colorfulgui.object.action.UpdateItem;
import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DynamicItem extends GuiItem {

    private UpdateItem<UpdateItemEvent> onUpdate;

    public DynamicItem() {
    }

    public DynamicItem(ItemStack itemStack, GuiAction<InventoryClickEvent> action, UpdateItem<UpdateItemEvent> onUpdate) {
        super(itemStack, action);
        this.onUpdate = onUpdate;
    }

    public DynamicItem(UUID uniqueId, ItemStack itemStack, GuiAction<InventoryClickEvent> action, UpdateItem<UpdateItemEvent> onUpdate) {
        super(uniqueId, itemStack, action);
        this.onUpdate = onUpdate;
    }

    public UpdateItem<UpdateItemEvent> getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(UpdateItem<UpdateItemEvent> onUpdate) {
        this.onUpdate = onUpdate;
    }

}
