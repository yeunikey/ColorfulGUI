package me.xflyiwnl.colorfulgui.builder;

import me.xflyiwnl.colorfulgui.ColorfulGUI;
import me.xflyiwnl.colorfulgui.object.GuiAction;
import me.xflyiwnl.colorfulgui.object.GuiItem;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemBuilder {

    private Material material;

    private String name;
    private List<String> lore = Arrays.asList();

    private int amount = 1;
    private ItemFlag[] itemFlags;

    private boolean unbreakable = false;
    private int model = 0;

    private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private GuiAction<InventoryClickEvent> action;


    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        this.itemFlags = flags;
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder model(int model) {
        this.model = model;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder action(GuiAction<InventoryClickEvent> action) {
        this.action = action;
        return this;
    }

    public GuiItem build() {

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(TextUtil.colorize(name));
        }

        itemMeta.setLore(TextUtil.colorize(lore));

        if (enchantments.isEmpty()) {
            enchantments.forEach((enchantment, integer) -> {
                itemMeta.addEnchant(enchantment, integer, true);
            });
        }

        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setCustomModelData(model);

        if (itemFlags != null) {
            itemMeta.addItemFlags(itemFlags);
        }

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(ColorfulGUI.getInstance(), "colorfulgui"), PersistentDataType.STRING, uuid.toString());

        itemStack.setItemMeta(itemMeta);
        return new GuiItem(uuid, itemStack, action);
    }

}
