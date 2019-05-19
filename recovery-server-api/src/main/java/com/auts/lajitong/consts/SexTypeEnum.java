package com.auts.lajitong.consts;

public enum SexTypeEnum {
    man(1, "男"),
    woman(2, "女"),
    unknow(0, "未知");

    SexTypeEnum(int value, String text) {
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
        for (SexTypeEnum c : SexTypeEnum.values()) {
            if (c.getValue() == value) {
                return c.getText();
            }
        }
        return null;
    }
}
