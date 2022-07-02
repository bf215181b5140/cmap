package com.changemyavatarparams.cmap.object;

import com.vaadin.flow.component.button.Button;

public class ParamsButton extends Button {

    private String param;
    private Object value;

    public ParamsButton(String param, Object value, String displayText){
        super(displayText);
        this.param = param;
        this.value = value;
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
}
