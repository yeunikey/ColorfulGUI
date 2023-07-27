package me.xflyiwnl.colorfulgui.builder.inventory;

import me.xflyiwnl.colorfulgui.object.Gui;
import me.xflyiwnl.colorfulgui.object.GuiMask;
import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;

import java.util.List;

public class StaticGuiBuilder implements GuiBuilder<Gui, StaticGuiBuilder> {

    private String title;
    private int rows;
    private GuiMask mask = new GuiMask();
    private ColorfulProvider<Gui> holder;

    public StaticGuiBuilder() {
    }

    @Override
    public StaticGuiBuilder holder(ColorfulProvider<Gui> holder) {
        this.holder = holder;
        return this;
    }

    @Override
    public StaticGuiBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public StaticGuiBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public StaticGuiBuilder mask(List<String> mask) {
        this.mask.setMask(mask);
        return this;
    }

    @Override
    public Gui build() {
        Gui gui = new Gui(getHolder(), getTitle(), getRows(), getMask());
        getMask().setGui(gui);
        getHolder().setGui(gui);
        getHolder().init();
        return gui;
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }

    public GuiMask getMask() {
        return mask;
    }

    public ColorfulProvider<Gui> getHolder() {
        return holder;
    }

}
