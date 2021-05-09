package org.orz.psol.model.JsonModel;


import lombok.Data;

import java.util.List;

@Data
public class CheckoutJson {

    String userId;
    List<CheckoutStore> storeList;
}
