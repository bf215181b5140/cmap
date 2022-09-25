package com.changemyavatarparams.cmap.util;

public class CmapUtil {

    public String hexColorAdjust(String hexColor, double modifier) {
        String result = "#";
        result = result.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(1, 3), 16) * modifier, 0), 255)));
        result = result.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(3, 5), 16) * modifier, 0), 255)));
        result = result.concat(Integer.toHexString((int) Math.min(Math.max(Integer.valueOf(hexColor.substring(5), 16) * modifier, 0), 255)));
        return result;
    }

}
