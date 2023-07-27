package me.xflyiwnl.colorfulgui.builder.inventory;

import me.xflyiwnl.colorfulgui.object.GuiMask;
import me.xflyiwnl.colorfulgui.object.PaginatedGui;
import me.xflyiwnl.colorfulgui.provider.ColorfulProvider;

import java.util.List;

public class DynamicGuiBuilder implements GuiBuilder<PaginatedGui, DynamicGuiBuilder> {

    private String title;
    private int rows;
    private GuiMask mask = new GuiMask();
    private ColorfulProvider<PaginatedGui> holder;

    public DynamicGuiBuilder() {
    }

    @Override
    public DynamicGuiBuilder holder(ColorfulProvider<PaginatedGui> holder) {
        this.holder = holder;
        return this;
    }

    @Override
    public DynamicGuiBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public DynamicGuiBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public DynamicGuiBuilder mask(List<String> mask) {
        this.mask.setMask(mask);
        return this;
    }

    @Override
    public PaginatedGui build() {
        PaginatedGui gui = new PaginatedGui(getHolder(), getTitle(), getRows(), getMask());
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

    public ColorfulProvider<PaginatedGui> getHolder() {
        return holder;
    }
}
