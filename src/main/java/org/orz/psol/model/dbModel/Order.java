package org.orz.psol.model.dbModel;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author ${author}
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="订单")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id(唯一确定一个订单)")
      private String id;

    @ApiModelProperty(value = "订单所属用户id")
    private String userId;

    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @ApiModelProperty(value = "收货地址id")
    private Integer addressId;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "订单总价格")
    private Float price;

    @ApiModelProperty(value = "下单时间")
    private Date orderTime;


}
