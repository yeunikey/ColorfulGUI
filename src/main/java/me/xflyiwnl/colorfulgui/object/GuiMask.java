package me.xflyiwnl.colorfulgui.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiMask {

    // Associated GUI
    private Gui gui;

    // Mask pattern
    private List<String> mask;
    // GuiItem mapped to their indicators
    private Map<String, GuiItem> maskItems = new HashMap<>();

    public GuiMask() {
    }

    /**
     * Creates a new GuiMask instance.
     *
     * @param gui  The GUI associated with this mask
     * @param mask The mask pattern represented as a list of strings
     **/
    public GuiMask(Gui gui, List<String> mask) {
        this.gui = gui;
        this.mask = mask;
    }

    /**
     * Calculates the slot index based on row and column.
     *
     * @param row The row index
     * @param col The column index
     * @return The slot index
     **/
    public int getSlotFromRowCol(final int row, final int col) {
        if (row >= 0 && row < mask.size() && col >= 0 && col < mask.get(row).length()) {
            return (col + (row * 9));
        } else {
            return -1;
        }
    }

    /**
     * Retrieves the list of slots occupied by items in the mask.
     *
     * @return The list of slot indices
     **/
    public List<Integer> slots() {
        List<Integer> slots = new ArrayList<>();

        getMaskItems().forEach((s, item) -> {
            for (int row = 0; row < mask.size(); row++) {
                String rowString = mask.get(row);
                for (int col = 0; col < rowString.length(); col++) {
                    if (rowString.charAt(col) == s.charAt(0)) {
                        int slot = row * 9 + col;
                        if (slot >= 0 && slot < getGui().getInventory().getSize()) {
                            slots.add(slot);
                        }
                    }
                }
            }
        });

        return slots;
    }

    /**
     * Retrieves the list of slots occupied by items with a specific indicator in the mask.
     *
     * @param indicator The indicator of the items to find
     * @return The list of slot indices
     **/
    public List<Integer> slots(String indicator) {
        List<Integer> slots = new ArrayList<>();

        for (int row = 0; row < mask.size(); row++) {
            String rowString = mask.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                if (rowString.charAt(col) == indicator.charAt(0)) {
                    int slot = row * 9 + col;
                    if (slot >= 0 && slot < getGui().getInventory().getSize()) {
                        slots.add(slot);
                    }
                }
            }
        }

        return slots;
    }

    /**
     * Adds a GuiItem to the mask with a specified indicator.
     *
     * @param indicator The indicator for the GuiItem
     * @param item      The GuiItem to add
     * @return The modified GuiMask instance
     **/
    public GuiMask addItem(String indicator, GuiItem item) {
        maskItems.put(indicator, item);
        return this;
    }

    /**
     * Retrieves the mask pattern.
     *
     * @return The mask pattern represented as a list of strings
     **/
    public List<String> getMask() {
        return mask;
    }

    /**
     * Sets the mask pattern.
     *
     * @param mask The mask pattern represented as a list of strings
     **/
    public void setMask(List<String> mask) {
        this.mask = mask;
    }

    /**
     * Retrieves the map of GuiItems with their indicators.
     *
     * @return The map of GuiItems with their indicators
     **/
    public Map<String, GuiItem> getMaskItems() {
        return maskItems;
    }

    /**
     * Sets the map of GuiItems with their indicators.
     *
     * @param maskItems The map of GuiItems with their indicators
     **/
    public void setMaskItems(Map<String, GuiItem> maskItems) {
        this.maskItems = maskItems;
    }

    /**
     * Retrieves the GUI associated with this mask.
     *
     * @return The GUI associated with this mask
     **/
    public Gui getGui() {
        return gui;
    }

    /**
     * Sets the GUI associated with this mask.
     *
     * @param gui The GUI associated with this mask
     **/
    public void setGui(Gui gui) {
        this.gui = gui;
    }

}
