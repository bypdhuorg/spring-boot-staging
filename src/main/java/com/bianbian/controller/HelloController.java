package com.bianbian.controller;

import com.bianbian.bean.bo.HelloBO;
import com.bianbian.bean.bo.HelloListBO;
import com.bianbian.bean.dto.HelloBaseDTO;
import com.bianbian.bean.dto.HelloDetailDTO;
import com.bianbian.bean.dto.HelloListDTO;
import com.bianbian.bean.vo.HelloListVO;
import com.bianbian.bean.vo.HelloVO;
import com.bianbian.common.validator.BindingResultValidator;
import com.bianbian.service.HelloService;
import com.bianbian.service.util.HelloHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bianbian
 * @date 2018/12/29
 */
@RestController
@RequestMapping("/v1/hello")
@Slf4j
public class HelloController {

    private final HelloService service;


    @Autowired
    public HelloController(HelloService service) {
        this.service = service;
    }

    /**
     * 创建
     *
     * @param vo            @see com.bianbian.bean.vo.HelloVO
     * @param bindingResult bindingResult
     * @return @see com.bianbian.bean.dto.HelloBaseDTO
     */
    @ApiOperation(value = "创建", notes = "根据提交的内容来创建")
    @ApiImplicitParam(name = "helloVO", value = "提交内容", required = true, dataType = "HelloVO")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HelloBaseDTO create(@Valid @RequestBody HelloVO vo, BindingResult bindingResult) {
        log.info("create(), request body is {}", vo.toString());

        BindingResultValidator.valid(bindingResult);

        HelloBO bo = HelloHelper.getBOFromVO(vo);

        //其他基本处理

        return service.save(bo);
    }

    /**
     * 更新
     *
     * @param id
     * @param vo
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "修改", notes = "根据提交的内容，修改")
    @ApiImplicitParam(name = "helloVO", value = "提交内容", required = true, dataType = "helloVO")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HelloBaseDTO update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody HelloVO vo,
            BindingResult bindingResult) {
        log.info("update(), id is {}, request body is {}", id, vo.toString());

        BindingResultValidator.valid(bindingResult);

        HelloBO bo = HelloHelper.getBOFromVO(vo);
        bo.setId(id);

        return service.update(bo);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取详情", notes = "获取对象详情")
    @ApiImplicitParam(name = "id", value = "id, 如8", required = true, dataType = "Long", defaultValue = "0L")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HelloDetailDTO get(@PathVariable(value = "id") Long id) {
        log.info("getDetail(), id is {}", id);

        return service.getDetail(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除对象")
    @ApiImplicitParam(name = "id", value = "id, 如10", required = true, dataType = "Long", defaultValue = "0L")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HelloBaseDTO delete(@PathVariable(value = "id") Long id) {
        log.info("delete(), id is {}", id);

        return service.delete(id);
    }


    /**
     * 获取列表
     *
     * @param page
     * @param pageSize
     * @param sort
     * @param name
     * @param remark
     * @param buId
     * @return
     */
    @ApiOperation(value = "获取列表", notes = "获取对象列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "Integer", defaultValue = "2"),
            @ApiImplicitParam(name = "sort", value = "排序，格式如： -createTime,id。 勿需多说，自己体味", dataType = "String"),
            @ApiImplicitParam(name = "bu_id", value = "BU id。多个用英文逗号隔开", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public HelloListDTO list(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "bu_id", required = false) String buId
    ) {
        HelloListVO vo = new HelloListVO(page, pageSize, sort, buId, name, remark);

        log.info("list(), request body is {}", vo.toString());

        HelloListBO bo = HelloHelper.getListBOFromListVO(vo);

        return service.list(bo);
    }
}
