package org.orz.psol.model.VO;

import lombok.Data;

import java.util.List;

@Data
public class OrderStore {
    String orderId;
    String storeId;
    String storeName;
    String logoImg;
    List<OrderProduct> products;
}
