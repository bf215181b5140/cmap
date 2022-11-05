package com.changemyavatarparams.cmap.object;

public class VerticalImageButton extends ImageButton {

    public VerticalImageButton(AvatarParameter avatarParameter) {
        super(avatarParameter);
        addClassName("VerticalImageButton");
    }

    public VerticalImageButton(AvatarParameter avatarParameter, String text) {
        super(avatarParameter, text);
        addClassName("VerticalImageButton");
    }
}
