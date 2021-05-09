package org.orz.psol.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-01-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CartItem对象", description="")
public class CartItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("choiceId")
    private String choiceId;

    @ApiModelProperty("choiceId")
    private String storeId;

    @ApiModelProperty("productId")
    private String productId;

    @ApiModelProperty("userId")
    private String userId;

    @ApiModelProperty("number")
    private Integer number;

    @ApiModelProperty("invalid")
    private int invalid;

    @Override
    public String toString() {
        return "CartItem{" +
                "choiceId='" + choiceId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", number=" + number +
                ", invalid=" + invalid +
                '}';
    }
}
