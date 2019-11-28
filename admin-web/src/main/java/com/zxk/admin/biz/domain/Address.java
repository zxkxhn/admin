package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年11月26日 20:59
 */

@Data
@TableName("address")
public class Address {

    private long id;
    private String name;
    private int lit;
}
