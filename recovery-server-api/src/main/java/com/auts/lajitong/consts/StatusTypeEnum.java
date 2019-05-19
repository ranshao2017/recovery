package com.auts.lajitong.consts;

public enum StatusTypeEnum {
    normal(0, "正常"),
    stop(1, "停用");

    StatusTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    private int value;
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static String getTextByValue(int value) {
        for (StatusTypeEnum c : StatusTypeEnum.values()) {
            if (c.getValue() == value) {
                return c.getText();
            }
        }
        return null;
    }
}
