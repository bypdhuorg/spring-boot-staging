package com.bianbian.common.enums;

/**
 * @author bianbian
 * @date 2018/12/6
 */
public enum ResponseStatusEnum {
    /**
     * 三个参数，分别为数字标示、英文含义、中文显示
     */
    SUCCESS(true, "success", "成功"),
    FAILURE(false, "failure", "失败");

    private boolean success;
    private String text;
    private String 中文;

    ResponseStatusEnum(boolean success, String name, String 中文) {
        this.success = success;
        this.text = name;
        this.中文 = 中文;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getText() {
        return this.text;
    }

    public String 获取中文() {
        return this.中文;
    }
}
