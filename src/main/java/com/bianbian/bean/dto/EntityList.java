package com.bianbian.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@ToString
public class EntityList<T> {

    @JsonProperty("total")
    private Long total;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("content")
    private List<T> content;

    public EntityList(Long total, Integer page, Integer pageSize, List<T> content) {
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.content = content;
    }

    public EntityList() {
    }
}
