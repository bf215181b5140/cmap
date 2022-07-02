package com.changemyavatarparams.cmap.object;

public class AvatarParameter {
    private String param;
    private Object value;

    public AvatarParameter(String param, Object value) {
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

    @Override
    public String toString() {
        return param;
    }
}
