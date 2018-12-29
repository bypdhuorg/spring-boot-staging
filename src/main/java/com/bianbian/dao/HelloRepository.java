package com.bianbian.dao;

import com.bianbian.bean.db.Hello;
import com.bianbian.dao.iface.IHelloRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public interface HelloRepository extends IHelloRepository, JpaRepository<Hello, Long>, JpaSpecificationExecutor<Hello> {
}
