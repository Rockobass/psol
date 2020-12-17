package org.orz.psol.model.VO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("展示商品的数据")
@Data
public class Display_product {
    String product_id;
    String cover_img;
    String name;
    String display_price;
    int sales;
}
