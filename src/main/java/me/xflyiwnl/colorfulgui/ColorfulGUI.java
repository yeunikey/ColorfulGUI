package me.xflyiwnl.colorfulgui;

import me.xflyiwnl.colorfulgui.builder.inventory.DynamicGuiBuilder;
import me.xflyiwnl.colorfulgui.builder.inventory.StaticGuiBuilder;
import me.xflyiwnl.colorfulgui.builder.item.DynamicItemBuilder;
import me.xflyiwnl.colorfulgui.builder.item.StaticItemBuilder;
import me.xflyiwnl.colorfulgui.listener.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorfulGUI {

    private static JavaPlugin instance;

    public ColorfulGUI() {
        System.out.println("TEST");
    }

    private void registerListeners() {
        instance.getServer().getPluginManager().registerEvents(new GuiListener(), instance);
    }

    public StaticItemBuilder staticItem() {
        return new StaticItemBuilder();
    }

    public DynamicItemBuilder dynamicItem() {
        return new DynamicItemBuilder();
    }

    public StaticGuiBuilder gui() {
        return new StaticGuiBuilder();
    }

    public DynamicGuiBuilder paginated() {
        return new DynamicGuiBuilder();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

}
