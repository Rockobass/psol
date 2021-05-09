package org.orz.psol.controller;

import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.pageModel.HomePageVO;
import org.orz.psol.model.pageModel.ProductDetailVO;
import org.orz.psol.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/common")
public class HomePageController {

    @Autowired
    HomePageService homeService;

    @ResponseBody
    @GetMapping("/home")
    HomePageVO getHomeData() {
        return homeService.getHomeData();
    }

    @ResponseBody
    @GetMapping("/type")
    List<Product> getTypeData(@RequestParam String type,@RequestParam int page) {
        return homeService.loadProduct(type, page);
    }

    @ResponseBody
    @GetMapping("/detail")
    ProductDetailVO getProductDetail(@RequestParam String id) {
        return homeService.getProductDetail(id);
    }

    @GetMapping("/store")
    String store(@RequestParam String userId) {

        return "store";
    }

    @GetMapping("/profile")
    String profile(@RequestParam String userId) {

        return "profile";
    }

    @GetMapping("/myOrder")
    String order(@RequestParam String userId) {
        return "myorder";
    }

}
