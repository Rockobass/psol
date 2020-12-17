package org.orz.psol.model.pageModel;

import lombok.Data;
import org.orz.psol.model.VO.OrderStore;

import java.util.List;

@Data
public class AllOrderVO {
    List<OrderStore> orders;
}
