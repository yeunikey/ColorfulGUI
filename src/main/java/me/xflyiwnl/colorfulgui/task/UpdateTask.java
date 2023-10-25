package me.xflyiwnl.colorfulgui.task;

import me.xflyiwnl.colorfulgui.object.DynamicItem;
import me.xflyiwnl.colorfulgui.object.GuiItem;
import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;
import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;
import java.util.Map;

public class UpdateTask extends BukkitRunnable {

    private JavaPlugin plugin;
    private int updateTime;
    private ColorfulProvider<?> provider;

    public UpdateTask(JavaPlugin plugin, int updateTime, ColorfulProvider<?> provider) {
        this.plugin = plugin;
        this.updateTime = updateTime;
        this.provider = provider;
    }

    public void startTask() {
        this.runTaskTimer(plugin, 0, updateTime);
    }

    @Override
    public void run() {
        provider.update();

        Map<Integer, GuiItem> slotItems = provider.getGui().getSlotItems();
        for (Integer id : slotItems.keySet()) {
            GuiItem item = slotItems.get(id);
            updateItem(item);
        }
        LinkedList<GuiItem> addItems = provider.getGui().getAddItems();
        for (GuiItem item : addItems) {
            updateItem(item);
        }
        provider.getGui().fill();
    }

    public void updateItem(GuiItem item) {
        if (item instanceof DynamicItem) {
            UpdateItemEvent event = new UpdateItemEvent(item.getItemStack());
            ((DynamicItem) item).getOnUpdate().execute(event);
            item.setItemStack(event.getItem());
        }
    }

}
