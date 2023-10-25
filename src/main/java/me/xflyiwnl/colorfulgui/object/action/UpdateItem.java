package me.xflyiwnl.colorfulgui.object.action;

import me.xflyiwnl.colorfulgui.object.event.UpdateItemEvent;

public interface UpdateItem<T extends UpdateItemEvent> {

    void execute(T event);

}
