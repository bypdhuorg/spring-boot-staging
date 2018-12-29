package com.bianbian.bean.common;

import com.bianbian.bean.db.Hello;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 可以用来对数据库数据进行一定处理，返回给其他页面。
 * 如将时间变为时间戳返回。
 *
 * @author bianbian
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloDetail extends Hello {

    private Long createTimeStamp;
    private Long updateTimeStamp;

    public HelloDetail(Hello hello) {
        super();
        BeanUtils.copyProperties(hello, this);

        if (this.getCreateTime() != null) {
            this.setCreateTimeStamp(this.getCreateTime().getTime());
        }
        if (this.getUpdateTime() != null) {
            this.setUpdateTimeStamp(this.getUpdateTime().getTime());
        }

        // 其他处理。例如隐藏一些敏感信息等。
    }

    public HelloDetail() {
        super();
    }

    @Override
    public String toString() {
        return String.format("HelloDetail(createTimeStamp=%s, updateTimeStamp=%s, Hello=%s)", createTimeStamp, updateTimeStamp, super.toString());
    }

}
