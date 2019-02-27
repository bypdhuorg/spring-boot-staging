package com.bianbian.modules.test.service;

import com.bianbian.base.BaseService;
import com.bianbian.bean.vo.SearchTimeVo;
import com.bianbian.modules.test.entity.TestR3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户2接口
 * @author God
 */
public interface TestR3Service extends BaseService<TestR3,String> {

    /**
     * 多条件分页获取(用户2)
     * @param testR3
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<TestR3> findByCondition(TestR3 testR3, SearchTimeVo searchVo, Pageable pageable);
}