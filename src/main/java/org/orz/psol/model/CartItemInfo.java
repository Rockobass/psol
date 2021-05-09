package org.orz.psol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.orz.psol.model.dbModel.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CartItem对象", description="")
public class CartItemInfo implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty("choiceId")
    private String choiceId;

    @ApiModelProperty("product_name")
    private String product_name;

    @ApiModelProperty("store_name")
    private String store_name;

    @ApiModelProperty("price")
    private double price;

    @ApiModelProperty("number")
    private int number;

    @ApiModelProperty("product")
    private Product product;


}
