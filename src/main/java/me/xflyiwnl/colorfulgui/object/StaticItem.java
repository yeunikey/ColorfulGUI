package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.object.action.GuiAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class StaticItem extends GuiItem {

    public StaticItem() {
    }

    public StaticItem(ItemStack itemStack, GuiAction<InventoryClickEvent> action) {
        super(itemStack, action);
    }

    public StaticItem(UUID uniqueId, ItemStack itemStack, GuiAction<InventoryClickEvent> action) {
        super(uniqueId, itemStack, action);
    }

}
