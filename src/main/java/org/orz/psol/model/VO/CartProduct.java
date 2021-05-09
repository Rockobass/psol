package org.orz.psol.model.VO;

import lombok.Data;

@Data
public class CartProduct {
    String productId;
    String choiceId;
    String coverImg;
    double price;
    int number;
    String productName;
    String choiceName;
    int storage;
    boolean selected;
}
