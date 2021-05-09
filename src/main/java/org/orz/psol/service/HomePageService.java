package org.orz.psol.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.orz.psol.mapper.HomeMapper;
import org.orz.psol.mapper.dbMapper.ProductChoiceMapper;
import org.orz.psol.mapper.dbMapper.ProductMapper;
import org.orz.psol.mapper.dbMapper.ServiceMapper;
import org.orz.psol.model.VO.DisplayProduct;
import org.orz.psol.model.VO.ProductArg;
import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.dbModel.ProductChoice;
import org.orz.psol.model.pageModel.HomePageVO;
import org.orz.psol.model.pageModel.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HomePageService {
    @Autowired
    HomeMapper homeMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ServiceMapper serviceMapper;
    @Autowired
    ProductChoiceMapper choiceMapper;
    @Autowired
    CartItemService cartItemService;

    public List<Product> loadProduct(String type, int current) {
        int size = 20;
        if (current == 1)
            size = 40;
        IPage<Product> page = new Page<>(current, size);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        page = productMapper.selectPage(page, wrapper);
        return page.getRecords();
    }

    public HomePageVO getHomeData() {
        HomePageVO homePageVO = new HomePageVO();

        String[] swipperUrls = homeMapper.getSwipperUrls();
        List<String> swipperList = new ArrayList<>(Arrays.asList(swipperUrls));
        homePageVO.setSwipper(swipperList);

        DisplayProduct[] leftProd = productMapper.getRandomFive();
        List<DisplayProduct> left = new ArrayList<>(Arrays.asList(leftProd));
        homePageVO.setLeftGoods(left);

        DisplayProduct[] rightProd = productMapper.getRandomFive();
        List<DisplayProduct> right = new ArrayList<>(Arrays.asList(rightProd));
        homePageVO.setRightGoods(right);

        List<Product> products = loadProduct("1",1);



        homePageVO.setGoods(products);



        return homePageVO;
    }

    public ProductDetailVO getProductDetail(String id) {
        ProductDetailVO productDetail = new ProductDetailVO();
        Product p = productMapper.getProductById(id);
        productDetail.setId(id);
        productDetail.setName(p.getName());
        productDetail.setCoverImg(p.getCoverImg());
        productDetail.setStoreId(p.getStoreId());
        productDetail.setStoreName(productMapper.getStoreNameByStoreId(p.getStoreId()));
        productDetail.setDisplayPrice(p.getLowPrice());
        productDetail.setSales(p.getSales());
        productDetail.setAddress(p.getAddress());
        productDetail.setPriceRange(p.getPriceRange());
        productDetail.setFreight(p.getFreight());
        productDetail.setDescription(p.getDescription());
        productDetail.setType(p.getType());
        List<ProductArg> args = new ArrayList<>();
        String[] params = new String[0];
        try {params = p.getProductParams().split("\\$");}catch (Exception e){}
        for (int i = 1; i < params.length; i++) {
            String[] str = params[i].split(":");
            ProductArg arg = new ProductArg();
            arg.setName(str[0]);
            arg.setValue(str[1]);
            args.add(arg);
        }
        productDetail.setArgs(args);
        int[] serviceIds = productMapper.getServiceIdByPID(id);
        List<Integer> sids = new ArrayList<>();
        for (int serviceId : serviceIds) {
            sids.add(serviceId);
        }
        List<org.orz.psol.model.dbModel.Service> ss = serviceMapper.selectBatchIds(sids);
        productDetail.setServices(ss);
        List<String> swippers = Arrays.asList(productMapper.getSwippersByPID(id));
        productDetail.setSwipperImgs(swippers);
        List<String> bodyImgs = Arrays.asList(productMapper.getBodyImgByPID(id));
        productDetail.setBodyImgs(bodyImgs);
        QueryWrapper<ProductChoice> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",id);
        List<ProductChoice> choices = choiceMapper.selectList(wrapper);
        productDetail.setChoices(choices);
        return productDetail;

    }
}
