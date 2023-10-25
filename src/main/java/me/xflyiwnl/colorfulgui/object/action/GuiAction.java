package me.xflyiwnl.colorfulgui.object.action;

import org.bukkit.event.Event;

public interface GuiAction<T extends Event> {

    void execute(T event);

}
