package com.bianbian.modules.test.entity;

import com.bianbian.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户2 业务数据
 *
 * @author God
 */
@Data
@Entity
@Table(name = "bt_test_r3")
@ApiModel(value = "用户2")
public class TestR3 extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private Integer age;

    private Long money;

    private Boolean marrage;

    private Date timeNow;

}