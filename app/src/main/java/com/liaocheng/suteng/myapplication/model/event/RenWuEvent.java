package com.liaocheng.suteng.myapplication.model.event;

public class RenWuEvent {
    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public int  tab;
    public RenWuEvent(int tab) {
        this.tab = tab;
    }
}
