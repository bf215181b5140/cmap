package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HpBarLayout extends HorizontalLayout {
    public Div hpBarFull = new Div();

    public HpBarLayout(){
        setId("HpBar");
        hpBarFull.setId("HpBarFull");
        hpBarFull.setWidth("100%");
        add(hpBarFull);
        setAlignItems(Alignment.CENTER);
    }

    public void setHpBarFull(int hp) {
        this.hpBarFull.setWidth((int)((hp * 10) * 0.5) + "%");
    }
}
