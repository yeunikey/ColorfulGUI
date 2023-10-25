package me.xflyiwnl.colorfulgui.object.event;

import org.bukkit.inventory.ItemStack;

public class UpdateItemEvent {


    private ItemStack item;

    public UpdateItemEvent(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

}
