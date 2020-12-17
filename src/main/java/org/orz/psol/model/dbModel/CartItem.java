package org.orz.psol.model.dbModel;

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
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CartItem对象", description="")
public class CartItem implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer choiceId;

    private String storeId;

    private String productId;

    private String userId;

    private Integer number;

    private Boolean invalid;


}
