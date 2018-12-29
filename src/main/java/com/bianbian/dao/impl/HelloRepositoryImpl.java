package com.bianbian.dao.impl;

import com.bianbian.bean.common.QueryEnum;
import com.bianbian.bean.db.Hello;
import com.bianbian.common.bean.BasePageSort;
import com.bianbian.dao.HelloRepository;
import com.bianbian.dao.iface.IHelloRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public class HelloRepositoryImpl implements IHelloRepository {

    private final HelloRepository helloRepository;

    @Autowired
    public HelloRepositoryImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public Page<Hello> findAllWithPage(BasePageSort basePageSort) {
        PageRequest pageRequest = buildPageRequest(basePageSort);
        if ((basePageSort.getQueryMap() == null) || (basePageSort.getQueryMap().size() == 0)) {
            return helloRepository.findAll(pageRequest);
        }
        Specification<Hello> specification = buildSpecification(basePageSort);
        return helloRepository.findAll(specification, pageRequest);
    }

    private PageRequest buildPageRequest(BasePageSort basePageSort) {
        return PageRequest.of(basePageSort.getPage() - 1, basePageSort.getPageSize(), basePageSort.getSort());
    }

    private Specification<Hello> buildSpecification(BasePageSort basePageSort) {
        return new Specification<Hello>() {
            @Override
            public Predicate toPredicate(Root<Hello> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String, Object> entry : basePageSort.getQueryMap().entrySet()) {
                    Object query = entry.getValue();
                    if (Objects.equals(entry.getKey(), QueryEnum.BU_ID.getName())) {
                        String buId0 = (String) query;
                        if (StringUtils.isNotEmpty(buId0)) {
                            String[] buIds = buId0.split(",");
                            Predicate[] p0 = new Predicate[buIds.length];
                            for (int i = 0; i < buIds.length; i++) {
                                p0[i] = criteriaBuilder.like(root.get(entry.getKey()).as(String.class), "%" + buIds[i].trim() + "%");
                            }
                            predicates.add(criteriaBuilder.or(p0));
                        }
                    }
                    if (Objects.equals(entry.getKey(), QueryEnum.NAME.getName())) {
                        String name = (String) query;
                        if (StringUtils.isNotEmpty(name)) {
                            predicates.add(criteriaBuilder.like(root.get(entry.getKey()).as(String.class), "%" + name + "%"));
                        }
                    }

                    if (Objects.equals(entry.getKey(), QueryEnum.REMARK.getName())) {
                        String remark = (String) query;
                        if (StringUtils.isNotEmpty(remark)) {
                            predicates.add(criteriaBuilder.like(root.get(entry.getKey()).as(String.class), "%" + remark + "%"));
                        }
                    }

                    if (Objects.equals(entry.getKey(), QueryEnum.TIME_TO.getName())) {
                        Date timeTo = getDateFromObject(query);
                        if (timeTo != null) {
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(QueryEnum.CREATE_TIME.getName()).as(Date.class), timeTo));
                        }
                    }
                    if (Objects.equals(entry.getKey(), QueryEnum.TIME_FROM.getName())) {
                        Date timeFrom = getDateFromObject(query);
                        if (timeFrom != null) {
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(QueryEnum.CREATE_TIME.getName()).as(Date.class), timeFrom));
                        }
                    }
                    // add more
                }

                Predicate[] p = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(p));
            }
        };
    }

    private Date getDateFromObject(Object object) {
        if (object == null) {
            return null;
        }
        Long timeStamp = (Long) object;
        if (timeStamp >= 99999999999L) {
            return new Date(timeStamp);
        } else if (timeStamp >= 0L) {
            return new Date(timeStamp * 1000);
        }
        return null;
    }
}

