package com.bianbian.common.enums;

/**
 * @author bianbian
 * @date 2018/12/6
 */
public enum ResponseStatusEnum {
    /**
     * 三个参数，分别为数字标示、英文含义、中文显示
     */
    SUCCESS(1, "success", "成功"),
    FAILURE(0, "failure", "失败");

    private Integer code;
    private String text;
    private String 中文;

    ResponseStatusEnum(Integer code, String name, String 中文) {
        this.code = code;
        this.text = name;
        this.中文 = 中文;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }

    public String 获取中文() {
        return this.中文;
    }
}
