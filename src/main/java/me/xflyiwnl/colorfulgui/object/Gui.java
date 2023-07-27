package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Gui {

    private String title;
    private int rows;

    private GuiMask mask;
    private List<GuiItem> items = new ArrayList<GuiItem>();

    private Inventory inventory;
    private ColorfulProvider<?> holder;

    public Gui() {}

    public Gui(ColorfulProvider<?> holder, String title, int rows, GuiMask mask) {
        this.title = title;
        this.rows = rows;
        this.mask = mask;

        inventory = Bukkit.createInventory(holder, rows * 9, TextUtil.colorize(title));
    }

    public void show(Player player) {
        drawMask();
        player.openInventory(getInventory());
    }

    public void setItem(int row, int col, GuiItem item) {
        inventory.setItem(getSlotFromRowCol(row, col), item.getItemStack());
    }

    public void setItem(int slot, GuiItem item) {
        getItems().add(item);
        inventory.setItem(slot, item.getItemStack());
    }

    public void addItem(GuiItem item) {
        getItems().add(item);
        inventory.addItem(item.getItemStack());
    }

    public int getSlotFromRowCol(final int row, final int col) {
        return (col + (row - 1) * 9) - 1;
    }

    public void addItem(GuiItem... item) {
        for (GuiItem guiItem : item) {
            getItems().add(guiItem);
            inventory.addItem(guiItem.getItemStack());
        }
    }

    public GuiItem getItem(UUID uuid) {
        for (GuiItem guiItem : items) {
            if (guiItem.getUniqueId().equals(uuid)) {
                return guiItem;
            }
        }
        return null;
    }

    public void drawMask() {
        getMask().getMaskItems().forEach((value, item) -> {
            List<Integer> slots = getMask().slots(value);
            if (!slots.isEmpty()) {
                slots.forEach(integer -> {
                    setItem(integer, item);
                });
            }
        });
    }

    public void drawMask(Inventory inventory) {
        getMask().getMaskItems().forEach((value, item) -> {
            List<Integer> slots = getMask().slots(value);
            if (!slots.isEmpty()) {
                slots.forEach(integer -> {
                    setItem(integer, item);
                });
            }
        });
    }

    public GuiMask getMask() {
        return mask;
    }

    public void setMask(GuiMask mask) {
        this.mask = mask;
    }

    public List<GuiItem> getItems() {
        return items;
    }

    public void setItems(List<GuiItem> items) {
        this.items = items;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }

    public ColorfulProvider<?> getHolder() {
        return holder;
    }
}
