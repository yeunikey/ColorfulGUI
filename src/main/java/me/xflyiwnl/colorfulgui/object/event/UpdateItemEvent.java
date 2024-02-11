package me.xflyiwnl.colorfulgui.object.event;

import me.xflyiwnl.colorfulgui.object.GuiItem;

public class UpdateItemEvent<T extends GuiItem> {

    private T item;

    public UpdateItemEvent(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

}
