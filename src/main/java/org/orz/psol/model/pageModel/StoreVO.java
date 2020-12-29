package org.orz.psol.model.pageModel;

import lombok.Data;
import org.orz.psol.model.VO.DisplayProduct;
import org.orz.psol.model.VO.LinkedImg;

import java.util.List;

@Data
public class StoreVO {
    String storeId;
    String storeName;
    String description;
    String logoImg;
    boolean isSpecial;
    // 广告图片，可以跳转到商品详情页
    List<LinkedImg> ads;
    List<LinkedImg> swipperImgs;
    List<DisplayProduct> products;

}
