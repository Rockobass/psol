package org.orz.psol.model.pageModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.orz.psol.model.VO.Display_product;
import org.orz.psol.model.VO.LinkedImg;

import java.util.List;


@ApiModel("主页数据")
@Data
public class HomePageVO {
    List<LinkedImg> swipper;
    List<Display_product> leftGoods;
    List<Display_product> rightGoods;
    List<Display_product> Goods;
}
