package me.xflyiwnl.colorfulgui.object;

public class PageItem {

    private GuiItem item;
    private int page;
    private int slot;

    public PageItem(GuiItem item, int page, int slot) {
        this.item = item;
        this.page = page;
        this.slot = slot;
    }

    public GuiItem getItem() {
        return item;
    }

    public void setItem(GuiItem item) {
        this.item = item;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
