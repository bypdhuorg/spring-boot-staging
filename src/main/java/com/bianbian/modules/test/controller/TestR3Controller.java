package com.bianbian.modules.test.controller;

import com.bianbian.base.BaseController;
import com.bianbian.bean.vo.PageVo;
import com.bianbian.bean.vo.SearchTimeVo;
import com.bianbian.common.dto.BaseDTO;
import com.bianbian.common.utils.ResultUtil;
import com.bianbian.modules.test.entity.TestR3;
import com.bianbian.modules.test.service.TestR3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户2Controller层
 * @author God
 */
@Slf4j
@RestController
@Api(description = "用户2管理接口")
@RequestMapping("/testR3")
@Transactional
public class TestR3Controller extends BaseController<TestR3, String>{

    @Autowired
    private TestR3Service testR3Service;

    @Override
    public TestR3Service getService() {
        return testR3Service;
    }

    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public BaseDTO<Page<TestR3>> getByPage(
            @ModelAttribute TestR3 testR3,
            @ModelAttribute SearchTimeVo searchVo,
            @ModelAttribute PageVo page){

        Page<TestR3> data = getService().findByCondition(testR3,searchVo,initPage(page));
        return new ResultUtil<Page<TestR3>>().setData(data);
    }
}
