package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:19
 * @version 1.0
 */

@Data
@TableName("user")
public class User {

    private long id;
    private String name;
    private int cityId;
    private String phone;
    private boolean sex;
    private String email;
    private Date createTime;
    private String password;

}

