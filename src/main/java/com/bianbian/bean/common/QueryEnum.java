package com.bianbian.bean.common;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public enum  QueryEnum {
    /**
     * 从页面传过来的参数 和 数据库字段
     */
    CREATE_TIME("createTime"),
    TIME_FROM("timeFrom"),
    TIME_TO("timeTo"),
    BU_ID("buId"),
    NAME("name"),
    REMARK("remark");

    public String getName() {
        return this.name;
    }

    QueryEnum(String name) {
        this.name = name;
    }

    private String name;
}
