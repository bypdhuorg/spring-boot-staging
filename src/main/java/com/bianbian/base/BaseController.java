package com.bianbian.base;

import com.bianbian.bean.vo.PageVo;
import com.bianbian.bean.vo.SearchTimeVo;
import com.bianbian.common.dto.BaseDTO;
import com.bianbian.common.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author meibo
 */
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract BaseService<E,ID> getService();

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public BaseDTO<E> get(@PathVariable ID id){

        E entity = getService().get(id);
        if(entity==null){
            return new ResultUtil<E>().setErrorMsg("数据库没有该对象,id:"+id);
        }
        return new ResultUtil<E>().setData(entity);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public BaseDTO<List<E>> getAll(){

        List<E> list = getService().getAll();
        return new ResultUtil<List<E>>().setData(list);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public BaseDTO<E> create(@RequestBody E entity){

        E e = getService().save(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public BaseDTO<E> update(@RequestBody E entity,@PathVariable ID id){
        E orgEntity = getService().get(id);
        if(orgEntity==null){
            return new ResultUtil<E>().setErrorMsg("数据库没有该对象,id:"+id);
        }

        BeanUtils.copyProperties(entity,orgEntity, new String[] {"id","createBy","createTime","updateTime"});
        E e = getService().update(orgEntity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "通过id删除")
    public BaseDTO<Object> delete(@PathVariable ID id){
         getService().delete(id);

        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过id删除")
    public BaseDTO<Object> delAllByIds(@PathVariable ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }



    public Pageable initPage(PageVo page){

        Pageable pageable=null;
        int pageNumber=page.getPage();
        int pageSize=page.getPageSize();
        String sort=page.getSort();
        String order=page.getOrder();

        if(pageNumber<1){
            pageNumber=1;
        }
        if(pageSize<1){
            pageSize=10;
        }
        if(StringUtils.isNotBlank(sort)) {
            Sort.Direction d;
            if(StringUtils.isBlank(order)) {
                d = Sort.Direction.DESC;
            }else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = new Sort(d,sort);
            pageable = PageRequest.of(pageNumber-1, pageSize,s);
        }else {
            pageable = PageRequest.of(pageNumber-1, pageSize);
        }
        return pageable;
    }
}
