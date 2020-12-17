package org.orz.psol.model.pageModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.orz.psol.model.VO.CartStore;

import java.util.List;

@ApiModel("购物车数据")
@Data
public class CartVO {
    int total;
    int invalid_total;
    List<CartStore> stores;
}
