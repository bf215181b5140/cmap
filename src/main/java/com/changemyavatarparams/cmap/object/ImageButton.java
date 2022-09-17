package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImageButton extends Button {

    private String param;
    private Object value;
    private String bgColor;


    public ImageButton(ParamsButton paramsButton, String baseUrl) {
        super(paramsButton.getDisplayText());
        this.param = paramsButton.getParam();
        this.value = paramsButton.getValue();
        this.bgColor = paramsButton.getBgColor() != null ? paramsButton.getBgColor() : "#233348";

        addClassName("imageButton");
        baseUrl = baseUrl != null ? baseUrl : "images/buttons/v2/";
        getStyle().set("background-image", "url(\"" + baseUrl + this.param + (int) this.value + ".png\")," +
                "linear-gradient(" + this.bgColor + "," + colorConverter(this.bgColor, 0.6) + ")");
    }

    public String colorConverter(String hexColor, double modifier) {
        String rgbColor = "#";
        rgbColor = rgbColor.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(1, 3), 16) * modifier, 0), 255)));
        rgbColor = rgbColor.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(3, 5), 16) * modifier, 0), 255)));
        rgbColor = rgbColor.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(5), 16) * modifier, 0), 255)));
        return rgbColor;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
