package me.xflyiwnl.colorfulgui.builder.inventory;

import me.xflyiwnl.colorfulgui.object.Gui;
import me.xflyiwnl.colorfulgui.object.GuiMask;
import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public interface GuiBuilder<T extends Gui, B extends GuiBuilder<T, B>> {

    B holder(ColorfulProvider<T> holder);

    B title(String title);

    B rows(int rows);

    B mask(List<String> mask);

    T build();

}
