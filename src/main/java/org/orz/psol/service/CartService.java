package org.orz.psol.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orz.psol.mapper.CartItemMapper;
import org.orz.psol.mapper.dbMapper.ProductChoiceMapper;
import org.orz.psol.mapper.dbMapper.ProductMapper;
import org.orz.psol.mapper.dbMapper.StoreMapper;
import org.orz.psol.model.CartItem;
import org.orz.psol.model.JsonModel.CartItemGroup;
import org.orz.psol.model.VO.CartProduct;
import org.orz.psol.model.VO.CartStore;
import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.dbModel.ProductChoice;
import org.orz.psol.model.dbModel.Store;
import org.orz.psol.model.pageModel.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductChoiceMapper productChoiceMapper;

    public CartVO getCart(String userId) {
        CartVO cartVO = new CartVO();
        List<CartStore> stores = new ArrayList<>();
        int itemCount = 0;

        CartItemGroup[] gs = cartItemMapper.getItemGroups("016950a3c749447cb4859a2e4bc55bd8");
        for (int i = 0; i < gs.length; i++) {
            CartStore cartStore = new CartStore();
            cartStore.setSelected(false);
            String storeId = gs[i].getStoreId();
            QueryWrapper<Store> storeWrapper = new QueryWrapper<>();
            storeWrapper.eq("id", storeId).select("name","is_special");
            Store store = storeMapper.selectOne(storeWrapper);
            String storeName = store.getName();
            cartStore.setStoreId(storeId);
            cartStore.setStoreName(storeName);
            cartStore.setSpecial(store.getIsSpecial());

            List<CartProduct> products = new ArrayList<>();

            String[] choices = gs[i].getChoiceIds().split(",");

            for (int j = 0; j < choices.length; j++) {
                CartProduct cartProduct = new CartProduct();
                String choiceId = choices[j];
                QueryWrapper<ProductChoice> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("choice_id", choiceId).select("choice","price","storage");
                ProductChoice productChoice = productChoiceMapper.selectOne(wrapper2);
                String choiceName = productChoice.getChoice();
                int storage = productChoice.getStorage();
                QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
                wrapper.eq("choice_id", choiceId).select("product_id","number","invalid");
                CartItem item = cartItemMapper.selectOne(wrapper);
                String productId = item.getProductId();
                QueryWrapper<Product> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("id", productId).select("cover_img", "name");
                Product product = productMapper.selectOne(wrapper1);
                String productName = product.getName();
                String coverImg = product.getCoverImg();

                cartProduct.setStorage(storage);
                cartProduct.setProductId(productId);
                cartProduct.setChoiceId(choiceId);
                cartProduct.setCoverImg(coverImg);
                cartProduct.setPrice(productChoice.getPrice());
                cartProduct.setNumber(item.getNumber());
                cartProduct.setProductName(productName);
                cartProduct.setChoiceName(choiceName);
                cartProduct.setSelected(false);
                products.add(cartProduct);
                itemCount++;
            }

            cartStore.setProducts(products);
            stores.add(cartStore);
        }
        cartVO.setStores(stores);
        cartVO.setTotal(itemCount);
        cartVO.setInvalid_total(0);
        return cartVO;
    }
}
