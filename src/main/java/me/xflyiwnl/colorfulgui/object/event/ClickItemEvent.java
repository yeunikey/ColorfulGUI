package me.xflyiwnl.colorfulgui.object.event;

import me.xflyiwnl.colorfulgui.object.GuiItem;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickItemEvent {

    private GuiItem currentItem;
    private InventoryAction action;
    private ClickType click;
    private Inventory clickedInventory;
    private ItemStack cursor;
    private Integer slot;
    private InventoryType.SlotType slotType;

    public ClickItemEvent(GuiItem currentItem, InventoryAction action, ClickType click, Inventory clickedInventory, ItemStack cursor, Integer slot, InventoryType.SlotType slotType) {
        this.currentItem = currentItem;
        this.action = action;
        this.click = click;
        this.clickedInventory = clickedInventory;
        this.cursor = cursor;
        this.slot = slot;
        this.slotType = slotType;
    }

    public GuiItem getCurrentItem() {
        return currentItem;
    }

    public InventoryAction getAction() {
        return action;
    }

    public ClickType getClick() {
        return click;
    }

    public Inventory getClickedInventory() {
        return clickedInventory;
    }

    public ItemStack getCursor() {
        return cursor;
    }

    public Integer getSlot() {
        return slot;
    }

    public InventoryType.SlotType getSlotType() {
        return slotType;
    }
}
