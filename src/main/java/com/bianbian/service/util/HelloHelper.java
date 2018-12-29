package com.bianbian.service.util;

import com.bianbian.bean.bo.HelloBO;
import com.bianbian.bean.bo.HelloListBO;
import com.bianbian.bean.common.HelloDetail;
import com.bianbian.bean.db.Hello;
import com.bianbian.bean.vo.HelloListVO;
import com.bianbian.bean.vo.HelloVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bianbian
 * @date 2018/12/29
 */
public class HelloHelper {

    public static HelloBO getBOFromVO(HelloVO helloVO) {
        HelloBO helloBO = new HelloBO();
        BeanUtils.copyProperties(helloVO, helloBO);
        // 其他处理
        return helloBO;
    }

    public static HelloListBO getListBOFromListVO(HelloListVO helloListVO) {
        HelloListBO helloListBO = new HelloListBO();
        BeanUtils.copyProperties(helloListVO, helloListBO);
        // 其他处理
        return helloListBO;
    }


    public static List<HelloDetail> getDetailListFromList(List<Hello> hellos) {
        List<HelloDetail> helloDetails = new ArrayList<>();

        for (Hello h : hellos) {
            HelloDetail helloDetail = new HelloDetail(h);
            // 其他处理，比如处理一些列表展示中不需要的信息。
            helloDetails.add(helloDetail);
        }
        return helloDetails;
    }
}
