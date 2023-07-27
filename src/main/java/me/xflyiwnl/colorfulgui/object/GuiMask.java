package me.xflyiwnl.colorfulgui.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.vm.ci.meta.JavaKind.Char;

public class GuiMask {

    private Gui gui;

    private List<String> mask;
    private Map<String, GuiItem> maskItems = new HashMap<String, GuiItem>();

    public GuiMask() {
    }

    public GuiMask(Gui gui, List<String> mask) {
        this.gui = gui;
        this.mask = mask;
    }

    public int getSlotFromRowCol(final int row, final int col) {
        if (row >= 0 && row < mask.size() && col >= 0 && col < mask.get(row).length()) {
            return (col + (row * 9));
        } else {
            return -1;
        }
    }

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

    public GuiMask addItem(String indicator, GuiItem item) {
        maskItems.put(indicator, item);
        return this;
    }

    public List<String> getMask() {
        return mask;
    }

    public void setMask(List<String> mask) {
        this.mask = mask;
    }

    public Map<String, GuiItem> getMaskItems() {
        return maskItems;
    }

    public void setMaskItems(Map<String, GuiItem> maskItems) {
        this.maskItems = maskItems;
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
