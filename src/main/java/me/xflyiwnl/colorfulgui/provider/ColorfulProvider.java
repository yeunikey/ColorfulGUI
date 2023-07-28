package me.xflyiwnl.colorfulgui.provider;

import me.xflyiwnl.colorfulgui.ColorfulGUI;
import me.xflyiwnl.colorfulgui.object.Gui;
import me.xflyiwnl.colorfulgui.task.UpdateTask;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class ColorfulProvider<T extends Gui> implements InventoryHolder {

    private Player player;
    private T gui;

    private boolean canUpdate = false;
    private int updateTime = 0;
    private UpdateTask task;

    public ColorfulProvider(Player player) {
        this.player = player;
    }

    public ColorfulProvider(Player player, int updateTime) {
        this.player = player;
        if (updateTime > 0) {
            this.canUpdate = true;
            this.updateTime = updateTime;
            this.task = new UpdateTask(ColorfulGUI.getInstance(), updateTime * 20, this);
        }
    }

    public void init() {

    }

    public void update() {

    }

    public void onClick(InventoryClickEvent event) {

    }

    public void onOpen(InventoryOpenEvent event) {

    }

    public void onClose(InventoryCloseEvent event) {

    }

    public void onDrag(InventoryDragEvent event) {
    }

    public void show() {
        getGui().show(player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return gui.getInventory();
    }

    public Player getPlayer() {
        return player;
    }

    public T getGui() {
        return gui;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public UpdateTask getTask() {
        return task;
    }

    public void setGui(T gui) {
        this.gui = gui;
    }
}
