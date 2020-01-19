package com.zxk.admin.biz.exception;

import com.zxk.core.exception.CustomizeException;

/**
 * Sys 异常处理
 *
 * @author xiaokun.zhang
 * Date:   2020年01月19日 16:32
 * @version 1.0
 */
public class SysException extends CustomizeException {

    private static final long serialVersionUID = 6882494958988846667L;

    public SysException(String msg) {
        super(msg);
    }
}
