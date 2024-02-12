package me.xflyiwnl.colorfulgui.object;

import me.xflyiwnl.colorfulgui.builder.ItemBuilder;
import me.xflyiwnl.colorfulgui.object.action.ClickAction;
import me.xflyiwnl.colorfulgui.object.event.ClickItemEvent;
import me.xflyiwnl.colorfulgui.ColorfulGUI;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public abstract class GuiItem {

    // unique id
    private UUID uniqueId = UUID.randomUUID();

    // itemstack
    private ItemStack itemStack;
    // runs this code when clicked
    private ClickAction<? extends ClickItemEvent> action;

    public GuiItem() {
    }

    /**
     * Constructor for a GuiItem.
     *
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     **/
    public GuiItem(ItemStack itemStack, ClickAction<? extends ClickItemEvent> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    /**
     * Constructor for a GuiItem with a specified unique ID.
     *
     * @param uniqueId  The unique ID for the item
     * @param itemStack The ItemStack to represent the item
     * @param action    The action to be performed when clicked
     **/
    public GuiItem(UUID uniqueId, ItemStack itemStack, ClickAction<? extends ClickItemEvent> action) {
        this.uniqueId = uniqueId;
        this.itemStack = itemStack;
        this.action = action;
    }

    /**
     * Retrieves a builder for creating ItemStacks.
     *
     * @return An ItemBuilder for creating ItemStacks
     **/
    public ItemBuilder<?> builder() {
        return null;
    }

    /**
     * Retrieves the unique ID of the GuiItem.
     *
     * @return The unique ID of the GuiItem
     **/
    public UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets the unique ID of the GuiItem.
     *
     * @param uniqueId The unique ID to be set
     **/
    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Retrieves the ItemStack of the GuiItem.
     *
     * @return The ItemStack of the GuiItem
     **/
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the ItemStack of the GuiItem.
     *
     * @param itemStack The ItemStack to be set
     **/
    public void setItemStack(ItemStack itemStack) {
        // Preserve the persistent data when setting the item stack
        NamespacedKey key = new NamespacedKey(ColorfulGUI.getInstance(), "colorfulgui");
        ItemMeta oldMeta = itemStack.getItemMeta();
        if (oldMeta != null && oldMeta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            ItemMeta newMeta = itemStack.getItemMeta();
            newMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, oldMeta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
            itemStack.setItemMeta(newMeta);
        }
        this.itemStack = itemStack;
    }

    /**
     * Retrieves the ClickAction of the GuiItem.
     *
     * @return The ClickAction of the GuiItem
     **/
    public ClickAction<? extends ClickItemEvent> getAction() {
        return action;
    }

    /**
     * Sets the ClickAction of the GuiItem.
     *
     * @param action The ClickAction to be set
     **/
    public void setAction(ClickAction<? extends ClickItemEvent> action) {
        this.action = action;
    }

}