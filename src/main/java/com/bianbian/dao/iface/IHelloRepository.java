package com.bianbian.dao.iface;

import com.bianbian.bean.db.Hello;
import com.bianbian.common.bean.BasePageSort;
import org.springframework.data.domain.Page;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public interface IHelloRepository {
    Page<Hello> findAllWithPage(BasePageSort basePageSort);

}
