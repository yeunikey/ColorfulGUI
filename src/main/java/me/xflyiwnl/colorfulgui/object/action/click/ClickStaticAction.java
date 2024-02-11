package me.xflyiwnl.colorfulgui.object.action.click;

import me.xflyiwnl.colorfulgui.object.action.ClickAction;
import me.xflyiwnl.colorfulgui.object.event.click.ClickStaticItemEvent;

public interface ClickStaticAction extends ClickAction<ClickStaticItemEvent> {

    @Override
    void execute(ClickStaticItemEvent event);
}
