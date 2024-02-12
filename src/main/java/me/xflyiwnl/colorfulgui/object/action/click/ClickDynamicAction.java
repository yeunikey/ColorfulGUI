package me.xflyiwnl.colorfulgui.object.action.click;

import me.xflyiwnl.colorfulgui.object.event.click.ClickDynamicItemEvent;
import me.xflyiwnl.colorfulgui.object.action.ClickAction;

public interface ClickDynamicAction extends ClickAction<ClickDynamicItemEvent> {
    @Override
    void execute(ClickDynamicItemEvent event);
}
