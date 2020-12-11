package org.orz.psol.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理员对象
 * </p>
 *
 * @author ${author}
 * @since 2020-12-11
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("管理员对象")
public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("user表id外键")
    private String id;

    @ApiModelProperty("管理员名称")
    private String name;

}
