package me.xflyiwnl.colorfulgui.task;

import me.xflyiwnl.colorfulgui.object.DynamicItem;
import me.xflyiwnl.colorfulgui.object.GuiItem;
import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;
import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class UpdateTask extends BukkitRunnable {

    private JavaPlugin plugin;
    private int updateTime;
    private ColorfulProvider<?> provider;
    private boolean started = false;

    public UpdateTask(JavaPlugin plugin, int updateTime, ColorfulProvider<?> provider) {
        this.plugin = plugin;
        this.updateTime = updateTime;
        this.provider = provider;
    }

    public void startTask() {
        if (started) return;
        started = true;
        this.runTaskTimer(plugin, 0, updateTime);
    }

    @Override
    public void run() {
        provider.update();

        Map<Integer, GuiItem> slotItems = new HashMap<Integer, GuiItem>(provider.getGui().getSetItems());
        for (Integer id : slotItems.keySet()) {
            GuiItem item = slotItems.get(id);
            updateItem(item);
        }
        LinkedList<GuiItem> addItems = new LinkedList<GuiItem>(provider.getGui().getAddItems());
        for (GuiItem item : addItems) {
            updateItem(item);
        }
        provider.getGui().render();
    }

    public void updateItem(GuiItem item) {
        if (item instanceof DynamicItem) {
            DynamicItem dynamicItem = (DynamicItem) item;
            if (dynamicItem.getOnUpdate() == null) return;
            UpdateItemEvent<DynamicItem> event = new UpdateItemEvent<DynamicItem>(dynamicItem);
            dynamicItem.getOnUpdate().execute(event);
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
