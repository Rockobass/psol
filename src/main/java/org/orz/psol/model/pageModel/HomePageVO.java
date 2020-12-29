package org.orz.psol.model.pageModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.orz.psol.model.VO.DisplayProduct;
import org.orz.psol.model.dbModel.Product;

import java.util.List;


@ApiModel("主页数据")
@Data
public class HomePageVO {
    List<String> swipper;
    List<DisplayProduct> leftGoods;
    List<DisplayProduct> rightGoods;
    List<Product> goods;
}
