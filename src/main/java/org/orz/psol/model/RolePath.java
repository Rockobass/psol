package org.orz.psol.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 配置相应权限可访问的路径
 * </p>
 *
 * @author ${author}
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RolePath对象", description="配置相应权限可访问的路径")
public class RolePath implements Serializable {

    private static final long serialVersionUID=1L;

    private String role;

    private String url;


}
