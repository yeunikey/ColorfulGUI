package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class Gui {

    // inventory title
    private String title;
    // inventory size (rows * 9)
    private int rows;
    // inventory mask
    private GuiMask mask;

    // items added with .setItem() method
    private Map<Integer, GuiItem> setItems = new HashMap<Integer, GuiItem>();
    // items added with .addItem() method
    private LinkedList<GuiItem> addItems = new LinkedList<GuiItem>();

    // all item indexes (only items in currentPage)
    private Map<UUID, Integer> itemIndex = new HashMap<UUID, Integer>();

    // inventory
    private Inventory inventory;
    // inventory's holder
    private ColorfulProvider<?> holder;

    public Gui() {}

    /**
     * The main constructor for initialize inventory
     *
     * @param holder    The {@link ColorfulProvider} class holder
     * @param title     The GUI title using {@link String}
     * @param rows      The GUI rows using {@link Integer} (rows * 9)
     * @param mask      The {@link GuiMask} to use
     **/
    public Gui(ColorfulProvider<?> holder, String title, int rows, GuiMask mask) {
        this.title = title;
        this.rows = rows;
        this.mask = mask;

        inventory = Bukkit.createInventory(holder, rows * 9, TextUtil.colorize(title));
    }

    /**
     * Drawing inventory, arranges {@link GuiItem}
     **/
    public void render() {
        inventory.clear();
        itemIndex.clear();
        drawMask();

        for (Integer slot : setItems.keySet()) {
            GuiItem item = setItems.get(slot);
            inventory.setItem(slot, item.getItemStack());
            itemIndex.put(item.getUniqueId(), slot);
        }
        for (GuiItem item : addItems) {
            int slot = searchSlot();
            if (slot == -1) break;
            inventory.setItem(slot, item.getItemStack());
            itemIndex.put(item.getUniqueId(), slot);
        }
    }

    /**
     * Showing inventory to {@link Player}
     *
     * @param player    The {@link Player} to show the GUI
     **/
    public void show(Player player) {
        render();
        player.openInventory(getInventory());
    }

    /**
     * Adding {@link GuiItem} to {@link GuiMask#getMask()} list
     *
     * @param indicator     The {@link String} to set {@link GuiItem}'s indicator
     * @param item          The {@link GuiItem} to add it in {@link GuiMask#getMask()}
     **/
    public void addMask(String indicator, GuiItem item) {
        getMask().addItem(indicator, item);
    }

    /**
     * Drawing {@link GuiMask#getMask()} in {@link Inventory}
     * Using in {@link #render()}
     *
     * @param inventory     The {@link Inventory} to draw {@link GuiMask#getMask()}
     **/
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

    /**
     * Alternative method for {@link #drawMask(Inventory)}
     **/
    public void drawMask() {
        this.drawMask(inventory);
    }

    /**
     * Get method for {@link GuiItem}
     *
     * @return return's {@link GuiItem}
     **/
    public GuiMask getMask() {
        return mask;
    }

    /**
     * Set method for {@link GuiItem}
     **/
    public void setMask(GuiMask mask) {
        this.mask = mask;
    }

    /**
     * Updating {@link GuiItem} in GUI
     *
     * @param item  The {@link GuiItem} to update
     **/
    public void updateItem(GuiItem item) {
        if (!itemIndex.containsKey(item.getUniqueId())) return;
        int slot = itemIndex.get(item.getUniqueId());
        inventory.setItem(slot, item.getItemStack());
    }

    /**
     * Re-creating {@link Inventory} with new title
     * This method can be laggy in loops
     *
     * @param title     The inventory title using {@link String}
     **/
    public void updateTitle(String title) {
        this.title = title;

        final List<HumanEntity> viewers = new ArrayList<HumanEntity>(inventory.getViewers());
        inventory = Bukkit.createInventory(holder, rows * 9, TextUtil.colorize(title));

        // rendering and opening new inventory for viewers
        for (final HumanEntity viewer : viewers) {
            if (viewer.isSleeping()) return;

            render();
            viewer.openInventory(inventory);
        }

    }

    /**
     * Get method for Inventory title
     *
     * @return return's {@link String}
     **/
    public String getTitle() {
        return title;
    }

    /**
     * Removing {@link GuiItem} from {@link Gui}
     *
     * @param item  the item to be deleted
     **/
    public void removeItem(GuiItem item) {

        for (Map.Entry<UUID, Integer> gitem : itemIndex.entrySet()) {
            if (gitem.getKey() == item.getUniqueId()) {
                inventory.setItem(gitem.getValue(), null);
            }
        }

        addItems.remove(item);
        setItems.values().removeIf(guiItem -> guiItem.getUniqueId().equals(item.getUniqueId()));
        itemIndex.keySet().removeIf(guiItem -> guiItem.equals(item.getUniqueId()));

        getMask().getMaskItems().values().removeIf(guiItem -> guiItem.getUniqueId().equals(item.getUniqueId()));

    }

    /**
     * Searching free slot in {@link Inventory}
     *
     * @return      The free slot
     **/
    public Integer searchSlot() {
        int slot = -1;
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) continue;
            slot = i;
            break;
        }
        return slot;
    }

    /**
     * Calculating slot with row and col
     *
     * @param row   The row using {@link Integer}
     * @param col   The col using {@link Integer}
     **/
    public int getSlotFromRowCol(final int row, final int col) {
        return (col + (row - 1) * 9) - 1;
    }

    /**
     * Get method for Inventory rows
     *
     * @return return's {@link Integer}
     **/
    public int getRows() {
        return rows;
    }

    /**
     * Searching {@link GuiItem} from {@link UUID}
     *
     * @param uuid      The {@link GuiItem}'s uniqueId using {@link UUID}
     * @return returning nothing, if {@link GuiItem} not found
     **/
    public GuiItem getItem(UUID uuid) {
        for (Integer id : setItems.keySet()) {
            GuiItem guiItem = setItems.get(id);
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

    /**
     * Adds {@link GuiItem} to {@link #getSetItems()}
     *
     * @param slot      The slot using {@link Integer}
     * @param item      The {@link GuiItem} to add it in {@link #getSetItems()}
     **/
    public void setItem(int slot, GuiItem item) {
        getSetItems().put(slot, item);
    }

    /**
     * Alternative method {@link #setItem(int, GuiItem)}
     *
     * @param row   The row using {@link Integer}
     * @param col   The col using {@link Integer}
     * @param item  The {@link GuiItem} to add it in {@link #getSetItems()}
     **/
    public void setItem(int row, int col, GuiItem item) {
        setItem(getSlotFromRowCol(row, col), item);
    }

    /**
     * Adds {@link GuiItem} to {@link #getAddItems()}
     *
     * @param item      The {@link GuiItem} to add it in {@link #getAddItems()} ()}
     **/
    public void addItem(GuiItem item) {
        getAddItems().add(item);
    }

    /**
     * Alternative method {@link #addItem(GuiItem)}
     *
     * @param item  The {@link GuiItem} array to iterate
     **/
    public void addItem(GuiItem... item) {
        for (GuiItem guiItem : item) {
            addItem(guiItem);
        }
    }

    /**
     * Get method for setted items
     *
     * @return return's {@link Map} where key is {@link Integer}, value is {@link GuiItem}
     **/
    public Map<Integer, GuiItem> getSetItems() {
        return setItems;
    }

    /**
     * Get method for added items
     *
     * @return return's {@link LinkedList} with {@link GuiItem} value
     **/
    public LinkedList<GuiItem> getAddItems() {
        return addItems;
    }

    /**
     * Get method for item indexes
     *
     * @return return's {@link Map} where key is {@link UUID}, value is {@link Integer}
     **/
    public Map<UUID, Integer> getItemIndex() {
        return itemIndex;
    }

    /**
     * Get method for Inventory
     *
     * @return return's {@link Inventory}
     **/
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get method for Inventory's holder
     *
     * @return return's {@link ColorfulProvider}
     **/
    public ColorfulProvider<?> getHolder() {
        return holder;
    }

    /**
     * Set method for Inventory's holder
     **/
    public void setHolder(ColorfulProvider<?> holder) {
        this.holder = holder;
    }

}
