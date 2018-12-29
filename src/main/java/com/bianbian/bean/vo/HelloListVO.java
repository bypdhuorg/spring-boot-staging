package com.bianbian.bean.vo;

import com.bianbian.bean.common.QueryEnum;
import com.bianbian.common.bean.BasePageSort;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloListVO extends BasePageSort {
    public HelloListVO(Integer page, Integer pageSize, String orderSort, String buId, String name, String remark) {
        super(page, pageSize, orderSort, null);

        Map<String, Object> queryMap = new HashMap<>();

        if (!StringUtils.isEmpty(buId)) {
            queryMap.put(QueryEnum.BU_ID.getName(), buId);
        }
        if (!StringUtils.isEmpty(name)) {
            queryMap.put(QueryEnum.NAME.getName(), name);
        }
        if (!StringUtils.isEmpty(remark)) {
            queryMap.put(QueryEnum.REMARK.getName(), remark);
        }
        // and so on.
        this.setQueryMap(queryMap);

        if (StringUtils.isEmpty(orderSort)) {
            this.handleSort("-" + QueryEnum.CREATE_TIME.getName());
        }
    }

    public HelloListVO() {
    }

    @Override
    public String toString() {
        return String.format("HelloListVO(BasePageSort=%s)", super.toString());
    }
}
