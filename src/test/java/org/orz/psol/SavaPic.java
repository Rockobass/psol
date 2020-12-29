package org.orz.psol;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.*;
import org.junit.jupiter.api.Test;
import org.orz.psol.mapper.HomeMapper;
import org.orz.psol.mapper.InsertMapper;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.mapper.dbMapper.*;
import org.orz.psol.model.JsonModel.ChoiceMap;
import org.orz.psol.model.JsonModel.ImageMap;
import org.orz.psol.model.JsonModel.JsonModel;
import org.orz.psol.model.User;
import org.orz.psol.model.dbModel.*;
import org.orz.psol.service.UserService;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.*;
import java.util.List;

@SpringBootTest
public class SavaPic {
    @Autowired
    InsertMapper insertMapper;
    @Autowired
    ProductChoiceMapper choiceMapper;
    @Autowired
    ServiceMapper serviceMapper;
    @Autowired
    ProductImgsMapper imgsMapper;
    @Autowired
    ProductServiceMapper productServiceMapper;

    @Test
    void saveCarousel() {
        String path = "C:\\MyProjects\\psol\\src\\main\\resources\\static\\pics\\carousel";

        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file: files) {
            String newName = UUIDGenerator.genPicID() + "."+ file.getName().split("\\.")[1];
            String full = path + "\\" + newName;
            File newFile = new File(full);
            file.renameTo(newFile);
            String url = "/static/carousel/" + newName;
            int count = insertMapper.insertIntoCarousel(url);

            System.out.println(count);
        }
    }

    void parseJson(String jsonPath) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        Random random = new Random();
        List<String> type = new ArrayList<>();
        type.add("1");
        type.add("2");
        type.add("3");
        type.add("4");
        type.add("5");
        JsonArray jsonArray = (JsonArray) parser.parse(new FileReader(jsonPath));
        for (JsonElement item : jsonArray) {
            JsonModel model = gson.fromJson(item, JsonModel.class);
//            System.out.println(model.toString());
            QueryWrapper<Product> wrapper = new QueryWrapper<>();
            wrapper.eq("id", model.getIid());
            Product product = productMapper.selectOne(wrapper);
            if (product != null)
                continue;
            product = new Product();
            product.setId(model.getIid());
            product.setStoreId(model.getStoreId());
            product.setName(model.getName());
            product.setCoverImg(model.getCoverImg());
            product.setAddress(model.getAddress());
            product.setHighPrice(model.getHighPrice());
            product.setLowPrice(model.getLowPrice());
            product.setDescription(model.getDescription());
            product.setProductParams(model.getProductParams());
            product.setSales(model.getSales());
            int i = random.nextInt(5);
            product.setType(type.get(i));
            product.setFreight(model.getFreight());
            product.setPriceRange(model.getPriceRange());
            productMapper.insert(product);
            List<ChoiceMap> choices = model.getChoices();
            for (ChoiceMap choiceMap : choices) {
                ProductChoice choice = new ProductChoice();
                choice.setChoiceId(choiceMap.getId());
                choice.setProductId(choiceMap.getProductId());
                choice.setPrice(choiceMap.getPrice());
                choice.setStorage(choiceMap.getStorage());
                choice.setChoiceImg(choiceMap.getImgUrl());
                choice.setChoice(choiceMap.getChoice());
                choiceMapper.insert(choice);
            }
            List<String> modelServices = model.getServices();
            for (String service : modelServices) {
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
                productService.setProductId(model.getIid());
                productService.setServiceId(service1.getId());
                productServiceMapper.insert(productService);
            }
            List<String> swiperUrls = model.getSwiperUrls();
            for (int j = 0; j < swiperUrls.size(); j++) {
                String url = swiperUrls.get(j);
                ProductImgs imgs = new ProductImgs();
                imgs.setUrl(url);
                imgs.setProductId(model.getIid());
                imgs.setIsSwipper(true);
                imgs.setSwipperOrder(j);
                imgsMapper.insert(imgs);
            }
            List<ImageMap> mapList = model.getImages();
            for (ImageMap image : mapList) {
                String key = image.getKey();
                List<String> urls = image.getUrlList();
                for (int j = 0; j < urls.size(); j++) {
                    ProductImgs imgs = new ProductImgs();
                    imgs.setUrl(urls.get(j));
                    imgs.setProductId(model.getIid());
                    imgs.setIsSwipper(false);
                    imgs.setArgOrder(j);
                    imgs.setArgKey(key);
                    imgsMapper.insert(imgs);
                }
            }

        }
    }

    @Test
    void tttt() throws FileNotFoundException {
        String path = "C:\\MyProjects\\psol\\src\\main\\resources\\stores";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            String jsonPath = file.getAbsolutePath();
            parseJson(jsonPath);
        }

    }

    @Autowired
    HomeMapper homeMapper;
    @Test
    void t() {
        String[] strs = homeMapper.getSwipperUrls();
        for (String str :
                strs) {
            System.out.println(str);

        }
    }



    @Autowired
    ProductMapper productMapper;

    @Test
    void t2() {
        IPage<Product> page = new Page<>(2, 6);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("type",1);
        page = productMapper.selectPage(page,wrapper);
        List<Product> products = page.getRecords();
        System.out.println(page.getTotal());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        for (Product p : products) {
            System.out.println(p);
        }
    }

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    StoreMapper storeMapper;

    @Test
    void t3() {
        String[] storeIds = insertMapper.getAllStoreId();
        for (int i = 0; i < storeIds.length; i++) {
            String storeId = storeIds[i];
            String uid = UUIDGenerator.getUUID();

            User user = new User();
            user.setId(uid);
            user.setUsername("tuser"+i);
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            user.setRole("ROLE_user");
            userMapper.insert(user);

            int x = userMapper.addPuser(uid,"tuser"+i,"t"+i,"3300000111111"+i,"1300012"+i);

            Store store = new Store();
            store.setId(storeId);
            store.setOwnerId(uid);
            store.setName("啥都卖的旗舰店"+i);
            store.setLocation("委内瑞拉");
            store.setDescription("这是啥都卖的旗舰店呀");
            store.setOpenTime(new Date(System.currentTimeMillis()));
            store.setTelephone("1300012"+i);
            store.setActivated(true);
            storeMapper.insert(store);
        }

    }

    @Test
    void t4() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id","1jw0sr2");
        Product p = productMapper.selectOne(wrapper);
        String params = p.getProductParams();
        String[] ps = params.split("\\$");
        for (int i = 1; i < ps.length; i++) {
            String[] str = ps[i].split(":");
            System.out.println("key:"+str[0]);
            System.out.println("value:"+str[1]);

        }
    }



}
