package org.orz.psol.model.pageModel;

import lombok.Data;
import org.orz.psol.model.VO.AddressVO;
import org.orz.psol.model.VO.OrderProduct;
import org.orz.psol.model.dbModel.ProductChoice;

import java.util.List;

@Data
public class OrderDetailVO {
    String ordreId;
    String storeName;
    String storeId;
    String createTime;
    String closeTime;
    String status;
    String logoImg;
    String telephone;
    AddressVO addressVO;
    boolean isClosed;
    List<OrderProduct> products;
}
