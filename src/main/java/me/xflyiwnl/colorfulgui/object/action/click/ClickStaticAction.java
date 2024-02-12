package me.xflyiwnl.colorfulgui.object.action.click;

import me.xflyiwnl.colorfulgui.object.event.click.ClickStaticItemEvent;
import me.xflyiwnl.colorfulgui.object.action.ClickAction;

public interface ClickStaticAction extends ClickAction<ClickStaticItemEvent> {

    @Override
    void execute(ClickStaticItemEvent event);
}
