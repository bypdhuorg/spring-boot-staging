package com.bianbian.service.impl;

import com.bianbian.bean.bo.HelloBO;
import com.bianbian.bean.bo.HelloListBO;
import com.bianbian.bean.common.HelloDetail;
import com.bianbian.bean.db.Hello;
import com.bianbian.bean.dto.HelloBaseDTO;
import com.bianbian.bean.dto.HelloDetailDTO;
import com.bianbian.bean.dto.HelloListDTO;
import com.bianbian.common.exception.ObjectNotFoundException;
import com.bianbian.dao.HelloRepository;
import com.bianbian.service.HelloService;
import com.bianbian.service.util.HelloHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    private final HelloRepository repository;

    @Autowired
    public HelloServiceImpl(HelloRepository repository) {
        this.repository = repository;
    }

    @Override
    public HelloBaseDTO save(HelloBO bo) {
        Hello entity = new Hello();
        BeanUtils.copyProperties(bo, entity);
        repository.save(entity);
        return new HelloBaseDTO();
    }

    @Override
    public HelloBaseDTO update(HelloBO bo) {
        Hello entity = shouldExist(bo.getId());

        // 修改指定内容
        entity.setName(bo.getName());
        entity.setContent(bo.getContent());
        entity.setStatus(bo.getStatus());
        entity.setRemark(bo.getRemark());

        repository.save(entity);

        return new HelloBaseDTO();
    }

    @Override
    public HelloDetailDTO getDetail(Long id) {
        Hello entity = shouldExist(id);
        return new HelloDetailDTO(entity);
    }

    @Override
    public HelloBaseDTO delete(Long id) {
        repository.deleteById(id);
        return new HelloBaseDTO();
    }

    @Override
    public HelloListDTO list(HelloListBO boList) {
        Page<Hello> entityPage = repository.findAllWithPage(boList);

        List<HelloDetail> helloDetailList = HelloHelper.getDetailListFromList(entityPage.getContent());

        return new HelloListDTO(entityPage.getTotalElements(), boList.getPage(), boList.getPageSize(), helloDetailList);
    }

    private Hello shouldExist(Long id) {
        Optional<Hello> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            return entityOptional.get();
        } else {
            throw new ObjectNotFoundException(String.format("找不到内容(id:%s)", id));
        }
    }
}
