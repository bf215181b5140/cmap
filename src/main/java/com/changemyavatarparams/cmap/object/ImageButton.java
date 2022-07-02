package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImageButton extends VerticalLayout {

    public ImageButton(ParamsButton paramsButton, String imageUrl) {
        add(new Image(imageUrl, "Example image"), paramsButton);
        setAlignItems(Alignment.CENTER);
        setWidth("auto");
    }
}
