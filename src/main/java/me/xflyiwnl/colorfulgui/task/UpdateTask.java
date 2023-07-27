package me.xflyiwnl.colorfulgui.task;

import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
    }

}
