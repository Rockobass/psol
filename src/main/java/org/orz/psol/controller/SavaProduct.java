package org.orz.psol.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orz.psol.mapper.dbMapper.*;
import org.orz.psol.model.RespBean;
import org.orz.psol.model.dbModel.*;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/save")
public class SavaProduct {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductChoiceMapper productChoiceMapper;
    @Autowired
    ProductImgsMapper productImgsMapper;
    @Autowired
    ServiceMapper serviceMapper;
    @Autowired
    ProductServiceMapper productServiceMapper;
    @Autowired
    ProductImgsMapper imgsMapper;

    @PostMapping("/product")
    RespBean saveProd(@RequestParam String productName, @RequestParam MultipartFile coverImg, @RequestParam String address,
                      @RequestParam float highPrice, @RequestParam float lowPrice,
                      @RequestParam String description, @RequestParam(required = false) String params,
                      @RequestParam String[] services,
                      @RequestParam String[] choiceName, @RequestParam float[] choicePrice,
                      @RequestParam int[] choiceStorage, @RequestParam MultipartFile[] swippers,
                      @RequestParam(required = false) MultipartFile[] bodyImgs
                      ) throws IOException {
        String packName = "fupin";
        String projDir = "C:\\\\MyProjects\\\\psol";

        String basePath = projDir+"\\src\\main\\resources\\static\\pics\\" + packName;
        File baseDir = new File(basePath);
        if (!baseDir.exists())
            baseDir.mkdir();
        String productId = UUIDGenerator.genProductID();
        String productPath = basePath + "\\" + productId;

        File proDir = new File(productPath);
        proDir.mkdir();
        String type = "扶贫";
        int sales = 8789;
        String freight = "免运费";
        String priceRange = "￥"+Float.toString(lowPrice) + "~￥" +Float.toString(highPrice);
        boolean invalid = false;
        String coverId = UUIDGenerator.genPicID();
        String coverImgPath = productPath + "\\" + coverId + ".jpg";
        coverImg.transferTo(new File(coverImgPath));
        String coverImgUrl = "/static/" + packName + "/" + productId + "/" + coverId + ".jpg";

        Product p = new Product();
        p.setId(productId);
        p.setName(productName);
        p.setCoverImg(coverImgUrl);
        p.setAddress(address);
        p.setHighPrice(highPrice);
        p.setLowPrice(lowPrice);
        p.setDescription(description);
        p.setProductParams(params);
        p.setSales(sales);
        p.setType(type);
        p.setFreight(freight);
        p.setPriceRange(priceRange);
        p.setInvalid(invalid);
        productMapper.insert(p);

        for (String service : services) {
            QueryWrapper<Service> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("name", service);
            Service service1 = serviceMapper.selectOne(wrapper1);
            if (service1 == null) {
                service1 = new Service();
                service1.setName(service);
                serviceMapper.insert(service1);
            }
            service1 = serviceMapper.selectOne(wrapper1);
            ProductService productService = new ProductService();
            productService.setProductId(productId);
            productService.setServiceId(service1.getId());
            productServiceMapper.insert(productService);
        }

        List<String> swiperUrls = new ArrayList<>();

        for (MultipartFile f :
                swippers) {
            String swiperId = UUIDGenerator.genPicID();
            String swiperPath = productPath + "\\" + swiperId + ".jpg";
            String swiperUrl = "/static/"+packName+"/" + productId + "/" + swiperId + ".jpg";
            f.transferTo(new File(swiperPath));
            swiperUrls.add(swiperUrl);
        }
        for (int j = 0; j < swiperUrls.size(); j++) {
            String url = swiperUrls.get(j);
            ProductImgs imgs = new ProductImgs();
            imgs.setUrl(url);
            imgs.setProductId(productId);
            imgs.setIsSwipper(true);
            imgs.setSwipperOrder(j);
            imgsMapper.insert(imgs);
        }

        List<String> bodyImgsUrl = new ArrayList<>();

        if (bodyImgs != null) {
            for (MultipartFile f :
                    bodyImgs) {
                String swiperId = UUIDGenerator.genPicID();
                String swiperPath = productPath + "\\" + swiperId + ".jpg";
                String swiperUrl = "/static/"+packName+"/" + productId + "/" + swiperId + ".jpg";
                f.transferTo(new File(swiperPath));
                bodyImgsUrl.add(swiperUrl);
            }
        }
        for (int j = 0; j < bodyImgsUrl.size(); j++) {
            String url = bodyImgsUrl.get(j);
            ProductImgs imgs = new ProductImgs();
            imgs.setUrl(url);
            imgs.setProductId(productId);
            imgs.setIsSwipper(false);
            imgs.setArgOrder(j);
            imgsMapper.insert(imgs);
        }

        for (int i = 0; i < choiceName.length; i++) {
            ProductChoice choice = new ProductChoice();
            choice.setChoiceId(UUIDGenerator.getUUID());
            choice.setProductId(productId);
            choice.setPrice(choicePrice[i]);
            choice.setStorage(choiceStorage[i]);
            choice.setChoice(choiceName[i]);
            productChoiceMapper.insert(choice);
        }





        return RespBean.ok("yes");
    }

}
