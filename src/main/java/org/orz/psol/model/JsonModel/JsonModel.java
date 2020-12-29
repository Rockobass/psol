package org.orz.psol.model.JsonModel;

import lombok.Data;

import java.util.List;

@Data
public class JsonModel {
    String storeId;
    String iid;
    String name;
    String coverImg;
    String address;
    Float highPrice;
    Float lowPrice;
    String description;
    String productParams;
    int sales;
    String freight;
    String priceRange;
    List<String> services;
    List<String> swiperUrls;
    List<ChoiceMap> choices;
    List<ImageMap> images;
}
