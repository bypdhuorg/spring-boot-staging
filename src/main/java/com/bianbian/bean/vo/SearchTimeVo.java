package com.bianbian.bean.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class SearchTimeVo implements Serializable {
    /**
     * 到毫秒的时间戳
     */
    private Long startDate;
    /**
     * 到毫秒的时间戳
     */
    private Long endDate;
}
