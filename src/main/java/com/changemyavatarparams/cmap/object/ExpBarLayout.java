package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ExpBarLayout extends HorizontalLayout {

    public Div expPoints = new Div();
    public Span expLevel = new Span();

    public ExpBarLayout(){
        setId("ExpBar");
        expPoints.setId("ExpBarFull");
        expPoints.setWidth("0%");
        expLevel.addClassName("ExpLevelText");
        expLevel.setText("");
        add(expPoints, expLevel);
        setAlignItems(Alignment.CENTER);
    }

    public Div getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int exp) {
        this.expPoints.setWidth((int)((exp * 10) * 0.5) + "%");
    }

    public int getExpLevel() {
        return Integer.parseInt(expLevel.getText());
    }

    public void setExpLevel(int level) {
        this.expLevel.setText(level > 0 ? String.valueOf(level) : "");
    }
}
