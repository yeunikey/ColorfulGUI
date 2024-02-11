
package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PaginatedGui extends Gui {

    // current showing page
    private int currentPage = 1;

    // allowed zones, contains slots
    protected List<Integer> allowedZone = new ArrayList<>();

    // pages and slots with items
    private final HashMap<Integer, LinkedHashMap<Integer, GuiItem>> pages = new HashMap<Integer, LinkedHashMap<Integer, GuiItem>>();

    /**
     * The main constructor for initialize inventory, has super
     *
     * @param holder    The {@link ColorfulProvider} class holder
     * @param title     The GUI title using {@link String}
     * @param rows      The GUI rows using {@link Integer} (rows * 9)
     * @param mask      The {@link GuiMask} to use
     **/
    public PaginatedGui(ColorfulProvider<?> holder, String title, int rows, GuiMask mask) {
        super(holder, title, rows, mask);

        pages.put(currentPage, new LinkedHashMap<>());
    }

    /**
     * Renders the inventory.
     **/
    public void render() {
        getInventory().clear();
        drawMask();
        settedItems();
        getItemIndex().clear();

        pages.get(currentPage).forEach((integer, item) -> {
            getInventory().setItem(integer, item.getItemStack());
            getItemIndex().put(item.getUniqueId(), integer);
        });

    }

    /**
     * Rendering the next page of the {@link PaginatedGui#getPages()} with {@link #getCurrentPage()}.
     **/
    public void next() {
        int nextPage = currentPage + 1;
        if (pages.containsKey(nextPage)) {
            currentPage = nextPage;
            render();
        }
    }

    /**
     * Rendering the previous page of the {@link PaginatedGui#getPages()} with {@link #getCurrentPage()}.
     **/
    public void previous() {
        int prevPage = currentPage - 1;
        if (pages.containsKey(prevPage)) {
            currentPage = prevPage;
            render();
        }
    }

    /**
     * Adds a new page to the {@link PaginatedGui#getPages()}.
     **/
    public void addPage() {
        int page = currentPage + 1;
        if (!pages.containsKey(page)) {
            pages.put(page, new LinkedHashMap<>());
        }
    }

    /**
     * Displays the inventory to the specified {@link Player}.
     *
     * @param player    The player to whom the inventory will be displayed
     **/
    @Override
    public void show(Player player) {
        sortItems();
        render();
        player.openInventory(getInventory());
    }

    /**
     * Retrieves the maximum page number.
     *
     * @return The maximum page number
     **/
    public int getMaxPage() {
        return pages.size();
    }

    /**
     * Retrieves the current page number.
     *
     * @return The current page number
     **/
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Calculating items in {@link Inventory} and changes the {@link #getAllowedZone()}
     **/
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

    /**
     * Arranges the {@link GuiItem} on the {@link #getPages()}
     **/
    protected void sortItems() {
        drawMask();
        settedItems();

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

    /**
     * Sets items in the {@link #getInventory()} from {@link #getSetItems()}
     **/
    protected void settedItems() {
        getSetItems().forEach((integer, item) -> {
            getInventory().setItem(integer, item.getItemStack());
        });
    }

    /**
     * Removes the specified {@link GuiItem} from the {@link PaginatedGui}.
     *
     * @param item  The item to be removed
     **/
    @Override
    public void removeItem(GuiItem item) {

        for (Map.Entry<UUID, Integer> gitem : getItemIndex().entrySet()) {
            if (gitem.getKey().equals(item.getUniqueId())) {
                getInventory().setItem(gitem.getValue(), null);
                System.out.println(gitem.getValue());
            }
        }

        for (Map.Entry<Integer, LinkedHashMap<Integer, GuiItem>> page : pages.entrySet()) {
            page.getValue().values().removeIf(guiItem -> guiItem.getUniqueId().equals(item.getUniqueId()));
        }

        getMask().getMaskItems().values().removeIf(guiItem -> guiItem.getUniqueId().equals(item.getUniqueId()));
        getItemIndex().keySet().removeIf(guiItem -> guiItem.equals(item.getUniqueId()));

    }

    /**
     * Retrieves the map of pages with their corresponding items.
     *
     * @return A map containing pages and their items
     **/
    public HashMap<Integer, LinkedHashMap<Integer, GuiItem>> getPages() {
        return pages;
    }

    /**
     * Retrieves the list of allowed zones (slots).
     *
     * @return A list of allowed zones
     **/
    public List<Integer> getAllowedZone() {
        return allowedZone;
    }

}