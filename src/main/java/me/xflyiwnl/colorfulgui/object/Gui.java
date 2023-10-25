package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class Gui {

    private String title;
    private int rows;

    private GuiMask mask;
    private Map<Integer, GuiItem> slotItems = new HashMap<Integer, GuiItem>();
    private LinkedList<GuiItem> addItems = new LinkedList<GuiItem>();

    private Inventory inventory;
    private ColorfulProvider<?> holder;

    public Gui() {}

    public Gui(ColorfulProvider<?> holder, String title, int rows, GuiMask mask) {
        this.title = title;
        this.rows = rows;
        this.mask = mask;

        inventory = Bukkit.createInventory(holder, rows * 9, TextUtil.colorize(title));
    }

    public void fill() {
        inventory.clear();
        drawMask();
        for (Integer slot : slotItems.keySet()) {
            GuiItem item = slotItems.get(slot);
            inventory.setItem(slot, item.getItemStack());
        }
        for (GuiItem item : addItems) {
            inventory.addItem(item.getItemStack());
        }
    }

    public void show(Player player) {
        fill();
        player.openInventory(getInventory());
    }

    public void setItem(int row, int col, GuiItem item) {
        slotItems.put(getSlotFromRowCol(row, col), item);
    }

    public void setItem(int slot, GuiItem item) {
        getSlotItems().put(slot, item);
    }

    public void addItem(GuiItem item) {
        getAddItems().add(item);
    }

    public int getSlotFromRowCol(final int row, final int col) {
        return (col + (row - 1) * 9) - 1;
    }

    public void addItem(GuiItem... item) {
        for (GuiItem guiItem : item) {
            getAddItems().add(guiItem);
        }
    }

    public GuiItem getItem(UUID uuid) {
        for (Integer id : slotItems.keySet()) {
            GuiItem guiItem = slotItems.get(id);
            if (guiItem.getUniqueId().equals(uuid)) {
                return guiItem;
            }
        }
        for (GuiItem guiItem : addItems) {
            if (guiItem.getUniqueId().equals(uuid)) {
                return guiItem;
            }
        }
        return null;
    }

    public void addMask(String indicator, GuiItem item) {
        getMask().addItem(indicator, item);
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

    public Map<Integer, GuiItem> getSlotItems() {
        return slotItems;
    }

    public LinkedList<GuiItem> getAddItems() {
        return addItems;
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
