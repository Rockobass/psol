package org.orz.psol.model.VO;

import lombok.Data;

import java.util.List;

@Data
public class CartStore {
    String storeName;
    String storeId;
    boolean selected;
    boolean special;
    List<CartProduct> products;
}
