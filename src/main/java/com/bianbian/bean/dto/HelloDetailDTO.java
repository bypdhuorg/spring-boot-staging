package com.bianbian.bean.dto;

import com.bianbian.bean.common.HelloDetail;
import com.bianbian.bean.db.Hello;
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
public class HelloDetailDTO extends BaseDTO<Object> {

    public HelloDetailDTO() {
        super();
        this.setCode(ResponseStatusEnum.SUCCESS.getCode());
        this.setMessage(ResponseStatusEnum.SUCCESS.getText());
    }

    public HelloDetailDTO(Hello hello) {
        this();

        HelloDetail helloDetail = new HelloDetail(hello);
        this.setData(helloDetail);
    }

    @Override
    public String toString() {
        return String.format("HelloDetailDTO(BaseDTO=%s)", super.toString());
    }
}
