package me.xflyiwnl.colorfulgui.object.action;

import org.bukkit.inventory.meta.ItemMeta;

public interface MetaChange<T extends ItemMeta> {
    void execute(T t);
}
