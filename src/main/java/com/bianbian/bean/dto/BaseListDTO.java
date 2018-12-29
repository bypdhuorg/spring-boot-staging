package com.bianbian.bean.dto;

import com.bianbian.common.dto.BaseDTO;
import com.bianbian.common.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseListDTO<T> extends BaseDTO<EntityList<T>> {

    BaseListDTO(Long total, Integer page, Integer pageSize, List<T> content) {
        this();
        EntityList<T> entityList = new EntityList<>(total, page, pageSize, content);
        this.setData(entityList);
    }

    private BaseListDTO() {
        super();
        this.setCode(ResponseStatusEnum.SUCCESS.getCode());
        this.setMessage(ResponseStatusEnum.SUCCESS.getText());
    }

    @Override
    public String toString() {
        return String.format("BaseListDTO(BaseDTO=%s)", super.toString());
    }
}
