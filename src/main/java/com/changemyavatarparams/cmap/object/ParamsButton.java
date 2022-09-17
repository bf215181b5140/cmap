package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.button.Button;

public class ParamsButton extends Button {

    private String param;
    private Object value;
    private String displayText;
    private String bgColor;

    public ParamsButton(String param, Object value, String displayText){
        super(displayText);
        this.displayText = displayText;
        this.param = param;
        this.value = value;
    }

    public ParamsButton(String param, Object value, String displayText, String bgColor){
        super(displayText);
        this.displayText = displayText;
        this.param = param;
        this.value = value;
        this.bgColor = bgColor;
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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
