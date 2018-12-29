package com.bianbian.service;

import com.bianbian.bean.bo.HelloBO;
import com.bianbian.bean.bo.HelloListBO;
import com.bianbian.bean.dto.HelloBaseDTO;
import com.bianbian.bean.dto.HelloDetailDTO;
import com.bianbian.bean.dto.HelloListDTO;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public interface HelloService {

    /**
     * 创建
     *
     * @param helloBO @see com.bianbian.bean.bo.HelloBO
     * @return @see com.bianbian.bean.dto.HelloBaseDTO
     */
    HelloBaseDTO save(HelloBO helloBO);

    /**
     * 修改
     *
     * @param helloBO @see com.bianbian.bean.bo.HelloBO
     * @return @see com.bianbian.bean.dto.HelloBaseDTO
     */
    HelloBaseDTO update(HelloBO helloBO);

    /**
     * 获取详情
     *
     * @param id id
     * @return @see com.bianbian.bean.dto.HelloDetailDTO
     */
    HelloDetailDTO getDetail(Long id);

    /**
     * 删除
     *
     * @param id id
     * @return @see com.bianbian.bean.dto.HelloBaseDTO
     */
    HelloBaseDTO delete(Long id);

    /**
     * 获取列表
     *
     * @param helloListBO @see com.bianbian.bean.bo.HelloListBO
     * @return @see com.bianbian.bean.dto.HelloListDTO
     */
    HelloListDTO list(HelloListBO helloListBO);
}
