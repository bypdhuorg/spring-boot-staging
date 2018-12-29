package com.bianbian.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author bianbian
 * @date 2018/12/10
 */
@Data
@ToString
public class BasePageSort {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pageSize")
    private Integer pageSize;

    private Sort sort;

    private Map<String, Object> queryMap;

    protected void handleSort(String orderSort) {
        if (StringUtils.isEmpty(orderSort)) {
            this.sort = Sort.by("id");
        } else {
            String[] orderSorts = StringUtils.stripAll(orderSort.split(","));
            List<Sort.Order> orderList = new ArrayList<>();
            for (String o : orderSorts) {
                if (o.startsWith("-")) {
                    if (o.length() == 1) {
                        continue;
                    }
                    orderList.add(Sort.Order.desc(o.substring(1, o.length())));
                } else {
                    orderList.add(Sort.Order.asc(o));
                }
            }
            this.sort = Sort.by(orderList);
        }
    }

    protected BasePageSort() {
    }

    public BasePageSort(Integer page, Integer pageSize, String orderSort, Map<String, Object> queryMap) {
        if (page == null || page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }

        this.queryMap = queryMap;

        this.handleSort(orderSort);
    }

}
