package com.dianwang.paipai.model;

/**
 * Created by leo on 2017/5/7.
 */
public class StradegyItem {

    private String text;
    private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static StradegyItem from(String text,String value) {
        StradegyItem stradegyItem = new StradegyItem();
        stradegyItem.setText(text);
        stradegyItem.setValue(value);
        return stradegyItem;
    }
}
