package me.xflyiwnl.colorfulgui.object.action;

import me.xflyiwnl.colorfulgui.object.event.ClickItemEvent;

public interface ClickAction<T extends ClickItemEvent> {

    void execute(T execute);

}
