package com.bianbian.bean.dto;

import com.bianbian.common.dto.BaseDTO;
import com.bianbian.common.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloBaseDTO extends BaseDTO<Object> {

    public HelloBaseDTO() {
        super();
        this.setCode(ResponseStatusEnum.SUCCESS.getCode());
        this.setMessage(ResponseStatusEnum.SUCCESS.getText());
    }

    @Override
    public String toString() {
        return String.format("HelloBaseDTO(BaseDTO=%s)", super.toString());
    }

}
