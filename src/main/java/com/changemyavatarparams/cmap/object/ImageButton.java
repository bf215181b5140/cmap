package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImageButton extends ParamButton {

    private String baseUrl = "images/buttons/";

    public ImageButton(AvatarParameter avatarParameter) {
        this(avatarParameter, null);
    }

    public ImageButton(AvatarParameter avatarParameter, String text) {
        super(avatarParameter, text);
        addClassName("imageButton");
        this.setBackground();
    }

    protected void setBackground(){
        getStyle().set("background-image", "url(\"" + baseUrl + this.getAvatarParameter().getParam() + this.getAvatarParameter().getValue() + ".png\")," +
                "linear-gradient(#8cb1db, #5a7faa)");
    }
}
