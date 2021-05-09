package org.orz.psol.model.VO;

import lombok.Data;
import org.orz.psol.model.dbModel.ProductChoice;

import java.util.List;

@Data
public class OrderProduct {
    String name;
    int number;
    double freight;
    String coverImg;
    ProductChoice choice;
    List<String> services;
}
