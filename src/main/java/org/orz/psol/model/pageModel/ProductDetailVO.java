package org.orz.psol.model.pageModel;

import lombok.Data;
import org.orz.psol.model.VO.LinkedImg;
import org.orz.psol.model.VO.NoLinkImg;
import org.orz.psol.model.VO.ProductArg;
import org.orz.psol.model.dbModel.ProductChoice;
import org.orz.psol.model.dbModel.Service;

import java.util.List;

@Data
public class ProductDetailVO {
    String id;
    String name;
    String storeName;
    String storeId;
    double displayPrice;
    int sales;
    String address;
    String freight;
    String description;
    String type;
    String priceRange;
    String coverImg;
    List<ProductArg> args;
    List<Service> services;
    List<String> swipperImgs;
    List<String> bodyImgs;
    List<ProductChoice> choices;
}
