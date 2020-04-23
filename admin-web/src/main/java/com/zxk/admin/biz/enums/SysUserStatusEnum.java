package com.zxk.admin.biz.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户状态
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 12:10
 */
@Getter
public enum SysUserStatusEnum {

    /**
     * value 返回码
     * desc  描述
     */
    normal(0, "正常"),
    Frozen(1, "冻结"),


    ;


    public static Map<Integer, SysUserStatusEnum> map = new HashMap<>();

    static {
        for (SysUserStatusEnum e : values()) {
            map.put(e.getValue(), e);
        }
    }

    private int value;
    private String desc;

    SysUserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static SysUserStatusEnum valueOf(int value) {
        return map.get(value);
    }

}
