package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.object.action.GuiAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GuiItem {

    private UUID uniqueId = UUID.randomUUID();

    private ItemStack itemStack;
    private GuiAction<InventoryClickEvent> action;

    public GuiItem() {
    }

    public GuiItem(ItemStack itemStack, GuiAction<InventoryClickEvent> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public GuiItem(UUID uniqueId, ItemStack itemStack, GuiAction<InventoryClickEvent> action) {
        this.uniqueId = uniqueId;
        this.itemStack = itemStack;
        this.action = action;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public GuiAction<InventoryClickEvent> getAction() {
        return action;
    }

    public void setAction(GuiAction<InventoryClickEvent> action) {
        this.action = action;
    }

}
