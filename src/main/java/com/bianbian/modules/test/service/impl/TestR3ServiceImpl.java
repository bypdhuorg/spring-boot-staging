package com.bianbian.modules.test.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bianbian.bean.vo.SearchTimeVo;
import com.bianbian.modules.test.dao.TestR3Dao;
import com.bianbian.modules.test.entity.TestR3;
import com.bianbian.modules.test.service.TestR3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户2接口实现
 * @author God
 */
@Slf4j
@Service
@Transactional
public class TestR3ServiceImpl implements TestR3Service {

    @Autowired
    private TestR3Dao testR3Dao;

    @Override
    public TestR3Dao getRepository() {
        return testR3Dao;
    }

    @Override
    public void saveCheck(TestR3 testR3){
       // if(getRepository().findByUserId(testR3.getUserId()) !=null){
       //     throw new UserClientException("XXX已经存在");
       // }
    };
    @Override
    public void updateCheck(TestR3 testR3){};
    @Override
    public void deleteCheck(TestR3 testR3){};

    @Override
    /**
     * 多条件分页获取(用户2)
     * @param testR3
     * @param searchVo
     * @param pageable
     * @return
     */
    public Page<TestR3> findByCondition(TestR3 testR3, SearchTimeVo searchVo, Pageable pageable) {
        return getRepository().findAll(new Specification<TestR3>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<TestR3> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //创建时间
                if(searchVo.getStartDate()!=null){
                    Date start = DateUtil.date(searchVo.getStartDate());
                    list.add(cb.greaterThanOrEqualTo(createTimeField, start));
                }
                if(searchVo.getEndDate()!=null){
                    Date end = DateUtil.date(searchVo.getEndDate());
                    list.add(cb.lessThanOrEqualTo(createTimeField, end));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }
}