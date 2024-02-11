package me.xflyiwnl.colorfulgui.object.event.click;

import me.xflyiwnl.colorfulgui.object.StaticItem;
import me.xflyiwnl.colorfulgui.object.event.ClickItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickStaticItemEvent extends ClickItemEvent {

    private StaticItem currentItem;

    public ClickStaticItemEvent(StaticItem currentItem, InventoryAction action, ClickType click, Inventory clickedInventory, ItemStack cursor, Integer slot, InventoryType.SlotType slotType) {
        super(currentItem, action, click, clickedInventory, cursor, slot, slotType);
        this.currentItem = currentItem;
    }

    @Override
    public StaticItem getCurrentItem() {
        return currentItem;
    }

}
