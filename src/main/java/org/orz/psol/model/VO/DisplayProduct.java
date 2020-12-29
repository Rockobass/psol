package org.orz.psol.model.VO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("展示商品的数据")
@Data
public class DisplayProduct {
    String id;
    String coverImg;
    String name;
    String highPrice;
    String lowPrice;
    int sales;
}
