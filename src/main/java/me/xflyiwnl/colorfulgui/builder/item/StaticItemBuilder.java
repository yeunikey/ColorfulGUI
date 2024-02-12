package me.xflyiwnl.colorfulgui.builder.item;

import me.xflyiwnl.colorfulgui.builder.ItemBuilder;
import me.xflyiwnl.colorfulgui.object.action.click.ClickStaticAction;
import me.xflyiwnl.colorfulgui.ColorfulGUI;
import me.xflyiwnl.colorfulgui.object.StaticItem;
import me.xflyiwnl.colorfulgui.object.action.MetaChange;
import me.xflyiwnl.colorfulgui.util.TextUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;

import java.util.*;

public class StaticItemBuilder implements ItemBuilder<StaticItem> {

    private StaticItem guiItem;
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private Material material;

    private String name;
    private List<String> lore = Arrays.asList();

    private int amount = 1;
    private ItemFlag[] itemFlags;

    private boolean unbreakable = false;
    private int model = 0;

    private boolean isBanner = false;
    private boolean isPotion = false;
    private boolean isSkull = false;
    private Player player;

    private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private ClickStaticAction action;

    private PotionData potionData;
    private Color color;

    private Map<Integer, Pattern> patternMap = new HashMap<Integer, Pattern>();
    private List<Pattern> patterns;

    private MetaChange<ItemMeta> metaChange;

    public StaticItemBuilder() {
    }

    public StaticItemBuilder from(StaticItem guiItem) {
        this.guiItem = guiItem;
        return this;
    }

    public StaticItemBuilder from(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public StaticItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public StaticItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StaticItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public StaticItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public StaticItemBuilder flags(ItemFlag... flags) {
        this.itemFlags = flags;
        return this;
    }

    public StaticItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public StaticItemBuilder model(int model) {
        this.model = model;
        return this;
    }

    public StaticItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public StaticItemBuilder action(ClickStaticAction action) {
        this.action = action;
        return this;
    }

    public StaticItemBuilder skull(Player player) {
        this.player = player;
        this.isSkull = true;
        return this;
    }

    public StaticItemBuilder potionData(PotionData potionData) {
        this.isPotion = true;
        this.potionData = potionData;
        return this;
    }

    public StaticItemBuilder color(Color color) {
        this.isPotion = true;
        this.color = color;
        return this;
    }

    public StaticItemBuilder pattern(int i, Pattern pattern) {
        this.isBanner = true;
        this.patternMap.put(i, pattern);
        return this;
    }

    public StaticItemBuilder patterns(List<Pattern> patterns) {
        this.isBanner = true;
        this.patterns = patterns;
        return this;
    }

    public StaticItemBuilder meta(MetaChange<ItemMeta> meta) {
        this.metaChange = meta;
        return this;
    }

    @Override
    public StaticItem build() {

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = guiItem != null ? guiItem.getItemStack() : this.itemStack;
        if (guiItem != null && itemStack == null ||
                guiItem == null && itemStack == null) {
            itemStack = new ItemStack(material, amount);
        }

        if (material != null) {
            itemStack.setType(material);
        }

        if (amount != 1) {
            itemStack.setAmount(amount);
        }

        ItemMeta itemMeta = this.itemMeta;
        if (itemMeta == null) itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(TextUtil.colorize(name));
        }

        itemMeta.setLore(TextUtil.colorize(lore));

        if (!enchantments.isEmpty()) {
            for (Enchantment enchantment : enchantments.keySet()) {
                int i = enchantments.get(enchantment);
                itemMeta.addEnchant(enchantment, i, true);
            }
        }

        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setCustomModelData(model);

        if (itemFlags != null) {
            itemMeta.addItemFlags(itemFlags);
        }

        if (guiItem != null) {
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(ColorfulGUI.getInstance(), "colorfulgui"), PersistentDataType.STRING, guiItem.getUniqueId().toString());
        } else {
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(ColorfulGUI.getInstance(), "colorfulgui"), PersistentDataType.STRING, uuid.toString());
        }

        if (metaChange != null) {
            metaChange.execute(itemMeta);
        }

        itemStack.setItemMeta(itemMeta);

        if (isSkull) {
            itemStack.setType(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setPlayerProfile(player.getPlayerProfile());
            itemStack.setItemMeta(skullMeta);
        }

        if (isPotion) {
            PotionMeta potionMeta = (PotionMeta) itemMeta;
            if (potionData != null) potionMeta.setBasePotionData(potionData);
            if (color != null) potionMeta.setColor(color);
            itemStack.setItemMeta(potionMeta);
        }

        if (isBanner) {
            BannerMeta bannerMeta = (BannerMeta) itemMeta;
            if (patterns != null) bannerMeta.setPatterns(patterns);
            if (!patternMap.isEmpty()) {
                patternMap.forEach((integer, pattern) -> {
                    bannerMeta.setPattern(integer, pattern);
                });
            }
            itemStack.setItemMeta(bannerMeta);
        }

        if (guiItem != null) {
            guiItem.setItemStack(itemStack);
            if (action != null)
                guiItem.setAction(action);
            return guiItem;
        } else {
            return new StaticItem(uuid, itemStack, action);
        }

    }
}
