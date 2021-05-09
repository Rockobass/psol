package org.orz.psol.model.JsonModel;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutStore {
    String storeId;
    float total;
    List<CheckoutChoice> choiceList;
}
