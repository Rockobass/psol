package org.orz.psol.model.VO;

import lombok.Data;

@Data
public class CartProduct {
    String productId;
    String name;
    String cover_img;
    double price;
    int number;
    String choiceName;
}
