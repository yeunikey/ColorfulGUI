package me.xflyiwnl.colorfulgui.builder;

import me.xflyiwnl.colorfulgui.object.GuiItem;

public interface ItemBuilder<T extends GuiItem> {

    T build();

}
