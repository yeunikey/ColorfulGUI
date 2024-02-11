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

public abstract class ColorfulProvider<T extends Gui> implements InventoryHolder {

    // Player
    private Player player;
    // Gui
    private T gui;

    // Can update
    private boolean canUpdate = false;
    // Update time
    private int updateTime = 0;
    // Task
    private UpdateTask task;

    /**
     * Constructs a ColorfulProvider with the given player.
     *
     * @param player The player associated with this provider
     **/
    public ColorfulProvider(Player player) {
        this.player = player;
    }

    /**
     * Constructs a ColorfulProvider with the given player and update time.
     *
     * @param player     The player associated with this provider
     * @param updateTime The time interval for updates, in seconds
     **/
    public ColorfulProvider(Player player, int updateTime) {
        this.player = player;
        if (updateTime > 0) {
            this.canUpdate = true;
            this.updateTime = updateTime;
            this.task = new UpdateTask(ColorfulGUI.getInstance(), updateTime * 20, this);
        }
    }

    /**
     * Initialize method
     **/
    public void init() {
    }

    /**
     * Update method, runs from {@link UpdateTask}
     **/
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

    /**
     * Showing {@link Gui} to {@link Player}
     **/
    public void show() {
        getGui().show(player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return getGui().getInventory();
    }

    /**
     * Retrieves the player associated with this provider.
     *
     * @return The associated player
     **/
    public Player getPlayer() {
        return player;
    }

    /**
     * Retrieves the GUI associated with this provider.
     *
     * @return The associated GUI
     **/
    public T getGui() {
        return gui;
    }

    /**
     * Checks if the provider can update.
     *
     * @return True if the provider can update, otherwise false
     **/
    public boolean isCanUpdate() {
        return canUpdate;
    }

    /**
     * Retrieves the update time interval.
     *
     * @return The update time interval in ticks
     **/
    public int getUpdateTime() {
        return updateTime;
    }

    /**
     * Retrieves the update task associated with this provider.
     *
     * @return The associated update task
     **/
    public UpdateTask getTask() {
        return task;
    }

    /**
     * Sets the GUI associated with this provider.
     *
     * @param gui The GUI to set
     **/
    public void setGui(T gui) {
        this.gui = gui;
    }
}
