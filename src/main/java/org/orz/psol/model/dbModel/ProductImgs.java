package org.orz.psol.model.dbModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductImgs对象", description="")
public class ProductImgs implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String productId;

    private Boolean isSwipper;

    private Integer swipperOrder;

    private Integer argOrder;

    private String argKey;


}
