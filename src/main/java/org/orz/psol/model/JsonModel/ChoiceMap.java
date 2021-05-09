package org.orz.psol.model.JsonModel;

import lombok.Data;

import java.io.File;

@Data
public class ChoiceMap {
    String productId;
    Float price;
    String choice;
    int storage;
    String id;
    String imgUrl;
}
