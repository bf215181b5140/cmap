package com.changemyavatarparams.cmap.object;

import java.util.function.Function;

public class AvatarParameter {
    private String param;
    private Object value;
    private Function<Integer, Integer> expCost;

    public AvatarParameter(String param, Object value) {
        this.param = param;
        this.value = value;
        this.setExpCost(0);
    }

    public AvatarParameter(String param, Object value, int expCost) {
        this.param = param;
        this.value = value;
        this.setExpCost(expCost);
    }

    public AvatarParameter(String param, Object value, Function<Integer, Integer> expCost) {
        this.param = param;
        this.value = value;
        this.expCost = expCost;
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

    public Function<Integer, Integer> getExpCost() {
        return expCost;
    }

    public void setExpCost(int expCost) {
        this.expCost = integer -> integer - expCost;
    }

    @Override
    public String toString() {
        return param;
    }
}
