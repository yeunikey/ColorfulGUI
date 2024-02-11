package me.xflyiwnl.colorfulgui.object.event.click;

import me.xflyiwnl.colorfulgui.object.DynamicItem;
import me.xflyiwnl.colorfulgui.object.event.ClickItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickDynamicItemEvent extends ClickItemEvent {

    private DynamicItem currentItem;

    public ClickDynamicItemEvent(DynamicItem currentItem, InventoryAction action, ClickType click, Inventory clickedInventory, ItemStack cursor, Integer slot, InventoryType.SlotType slotType) {
        super(currentItem, action, click, clickedInventory, cursor, slot, slotType);
        this.currentItem = currentItem;
    }

    public DynamicItem getCurrentItem() {
        return currentItem;
    }

}
