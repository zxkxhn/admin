package com.zxk.admin.biz.query;

import com.ss.core.common.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserQuery extends BaseQuery {
    private static final long serialVersionUID = -4112126868065568686L;

    @ApiModelProperty(value = "id", example = "1")
    private long id;

}
