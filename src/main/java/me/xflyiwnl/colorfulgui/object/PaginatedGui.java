package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PaginatedGui extends Gui {

    private int currentPage = 1;

    protected List<Integer> allowedZone = new ArrayList<>();

    private HashMap<Integer, LinkedHashMap<Integer, GuiItem>> pages = new HashMap<>();

    public PaginatedGui(ColorfulProvider<?> holder, String title, int rows, GuiMask mask) {
        super(holder, title, rows, mask);
        pages.put(currentPage, new LinkedHashMap<>());
    }

    public void addPage() {
        int page = currentPage + 1;
        if (!pages.containsKey(page)) {
            pages.put(page, new LinkedHashMap<>());
        }
    }

    public void next() {
        int nextPage = currentPage + 1;
        if (pages.containsKey(nextPage)) {
            currentPage = nextPage;
            fill();
        }
    }

    public void previous() {
        int prevPage = currentPage - 1;
        if (pages.containsKey(prevPage)) {
            currentPage = prevPage;
            fill();
        }
    }

    protected void calculatePerPageItems() {
        List<Integer> mask = getMask().slots();
        for (int i = 0; i < getInventory().getSize(); i++) {
            if (!mask.contains(i)) {
                ItemStack itemStack = getInventory().getItem(i);
                if (itemStack == null) {
                    allowedZone.add(i);
                }
            }
        }
    }

    protected void sortItems() {
        drawMask();
        staticItems();

        calculatePerPageItems();

        int page = currentPage;
        List<Integer> takenZone = new ArrayList<>();

        for (GuiItem item : getAddItems()) {
            for (Integer integer : allowedZone) {
                if (takenZone.size() == allowedZone.size()) {
                    takenZone.clear();
                    page += 1;
                    if (!pages.containsKey(page)) {
                        pages.put(page, new LinkedHashMap<>());
                    }
                }

                if (takenZone.contains(integer)) {
                    continue;
                }

                pages.get(page).put(integer, item);

                takenZone.add(integer);
                break;
            }

        }

    }

    protected void staticItems() {
        getSlotItems().forEach((integer, item) -> {
            getInventory().setItem(integer, item.getItemStack());
        });
    }

    public void fill() {
        getInventory().clear();
        drawMask();
        staticItems();

        pages.get(currentPage).forEach((integer, item) -> {
            getInventory().setItem(integer, item.getItemStack());
        });

    }

    @Override
    public void addItem(GuiItem... items) {
        for (GuiItem item : items) {
            addItem(item);
        }
    }

    @Override
    public void addItem(GuiItem item) {
        getAddItems().add(item);
    }

    @Override
    public void setItem(int row, int col, GuiItem item) {
        setItem(getSlotFromRowCol(row, col), item);
    }

    @Override
    public void setItem(int slot, GuiItem item) {
        getSlotItems().put(slot, item);
    }

    @Override
    public void show(Player player) {
        sortItems();
        fill();
        player.openInventory(getInventory());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public HashMap<Integer, LinkedHashMap<Integer, GuiItem>> getPages() {
        return pages;
    }

    public List<Integer> getAllowedZone() {
        return allowedZone;
    }

}
