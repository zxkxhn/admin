package com.zxk.admin.biz.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SysMenuTypeEnum {

    /**
     * value 返回码
     * desc  描述
     * 0：目录   1：菜单   2：按钮
     */
    DIRECTORY(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮")


    ;

    private int value;
    private String desc;

    SysMenuTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Map<Integer, SysMenuTypeEnum> map = new HashMap<>();

    static {
        for (SysMenuTypeEnum e : values()) {
            map.put(e.getValue(), e);
        }
    }


    public static SysMenuTypeEnum valueOf(int value) {
        return map.get(value);
    }

}
