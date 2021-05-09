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
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderItem对象", description="")
public class OrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    private String orderId;

    private String productId;

    private String choiceId;

    private Integer number;


}
