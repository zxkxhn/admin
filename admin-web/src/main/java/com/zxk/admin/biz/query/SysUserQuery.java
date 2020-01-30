package com.zxk.admin.biz.query;

import com.zxk.admin.biz.domain.SysUser;
import com.zxk.core.common.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserQuery extends BaseQuery<SysUser> {
    private static final long serialVersionUID = -4112126868065568686L;

    @ApiModelProperty(value = "id", example = "1")
    private long id;

}
