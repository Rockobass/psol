package org.orz.psol.model.pageModel;

import lombok.Data;
import org.orz.psol.model.VO.LinkedImg;
import org.orz.psol.model.VO.NoLinkImg;
import org.orz.psol.model.VO.ProductArg;
import org.orz.psol.model.dbModel.ProductChoice;

import java.util.List;

@Data
public class ProductDetailVO {
    String name;
    double displayPrice;
    double discount;
    int sales;
    String address;
    double freight;
    String description;
    String type;
    List<ProductArg> args;
    List<String> services;
    List<LinkedImg> swipperImgs;
    List<NoLinkImg> bodyImgs;
    List<ProductChoice> choices;
}
