package me.xflyiwnl.colorfulgui;

import me.xflyiwnl.colorfulgui.builder.ItemBuilder;
import me.xflyiwnl.colorfulgui.builder.inventory.DynamicGuiBuilder;
import me.xflyiwnl.colorfulgui.builder.inventory.StaticGuiBuilder;
import me.xflyiwnl.colorfulgui.listener.GuiListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ColorfulGUI {

    private static JavaPlugin instance;

    public ColorfulGUI(JavaPlugin plugin) {
        this.instance = plugin;

        registerListeners();

    }

    private void registerListeners() {
        instance.getServer().getPluginManager().registerEvents(new GuiListener(), instance);
    }

    public ItemBuilder item() {
        return new ItemBuilder();
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
