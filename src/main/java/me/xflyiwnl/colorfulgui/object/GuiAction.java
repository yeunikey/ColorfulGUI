package me.xflyiwnl.colorfulgui.object;

import org.bukkit.event.Event;

public interface GuiAction<T extends Event> {

    void execute(T event);

}
