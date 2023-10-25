package me.xflyiwnl.colorfulgui.builder.item;

import me.xflyiwnl.colorfulgui.ColorfulGUI;
import me.xflyiwnl.colorfulgui.builder.ItemBuilder;
import me.xflyiwnl.colorfulgui.object.DynamicItem;
import me.xflyiwnl.colorfulgui.object.action.GuiAction;
import me.xflyiwnl.colorfulgui.object.action.UpdateItem;
import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class DynamicItemBuilder extends ItemBuilder {

    private Material material;

    private String name;
    private List<String> lore = Arrays.asList();

    private int amount = 1;
    private ItemFlag[] itemFlags;

    private boolean unbreakable = false;
    private int model = 0;

    private boolean isSkull = false;
    private Player player;

    private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private GuiAction<InventoryClickEvent> action;
    private UpdateItem<UpdateItemEvent> onUpdate;

    public DynamicItemBuilder() {
    }

    public DynamicItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public DynamicItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DynamicItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public DynamicItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public DynamicItemBuilder flags(ItemFlag... flags) {
        this.itemFlags = flags;
        return this;
    }

    public DynamicItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public DynamicItemBuilder model(int model) {
        this.model = model;
        return this;
    }

    public DynamicItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public DynamicItemBuilder action(GuiAction<InventoryClickEvent> action) {
        this.action = action;
        return this;
    }

    public DynamicItemBuilder skull(Player player) {
        this.player = player;
        this.isSkull = true;
        return this;
    }

    public DynamicItemBuilder update(UpdateItem<UpdateItemEvent> onUpdate) {
        this.onUpdate = onUpdate;
        return this;
    }

    @Override
    public DynamicItem build() {

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(TextUtil.colorize(name));
        }

        itemMeta.setLore(TextUtil.colorize(lore));

        if (!enchantments.isEmpty()) {
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

        if (isSkull) {
            itemStack.setType(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setPlayerProfile(player.getPlayerProfile());
            itemStack.setItemMeta(skullMeta);
        }

        itemStack.setItemMeta(itemMeta);

        return new DynamicItem(uuid, itemStack, action, onUpdate);
    }
}
