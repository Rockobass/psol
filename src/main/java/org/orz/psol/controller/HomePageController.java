package org.orz.psol.controller;

import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.pageModel.HomePageVO;
import org.orz.psol.model.pageModel.ProductDetailVO;
import org.orz.psol.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class HomePageController {

    @Autowired
    HomePageService homeService;

    @GetMapping("/home")
    HomePageVO getHomeData() {
        return homeService.getHomeData();
    }

    @GetMapping("/type")
    List<Product> getTypeData(@RequestParam String type,@RequestParam int page) {
        return homeService.loadProduct(type, page);
    }

    @GetMapping("/detail")
    ProductDetailVO getProductDetail(@RequestParam String id) {
        return homeService.getProductDetail(id);
    }
}
