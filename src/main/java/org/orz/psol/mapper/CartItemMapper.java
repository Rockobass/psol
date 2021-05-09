package org.orz.psol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.orz.psol.model.CartItem;
import org.orz.psol.model.JsonModel.CartItemGroup;
import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.dbModel.ProductChoice;

import java.util.List;

public interface CartItemMapper extends BaseMapper<CartItem> {
    @Insert("insert into cart_item (choice_id, store_id, product_id, user_id, number,invalid) value ('${choiceId}', '${storeId}', '${productId}', '${userId}', '${number}','${invalid}')")
    int addCartItem(String choiceId, String storeId, String productId, String userId, int number, int invalid);

    @Select("select  number from cart_item where choice_id='${choiceId}' and user_id='${userId}' ")
    int selectNumberCartItem(String choiceId, String userId);

    @Update("update  cart_item set number='${number}' where choice_id='${choiceId}'  and user_id='${userId}' ")
    boolean updataCartItem(int number,String choiceId,String userId);

    @Delete("delete from cart_item where choice_id='${choiceId}' and user_id='${userId}'")
    boolean deleteCartItem(String choiceId, String userId);

    @Select("select choice_id, store_id, product_id, user_id, number,invalid from cart_item where user_id='${userId}'")
    List<CartItem> selectUserAllCartItem(String userId);

    @Select("select choice_id from cart_item where user_id='${userId}'")
    List<String> selectUserAllChoice_idCartItem(String userId);

    @Select("select  product_id from product_choice where choice_id='${choiceId}'")
    String slecetProductId(String choiceId);

    @Select("select  store_id from product where id='${product_id}'")
    String slecetStoreId(String product_id);

    @Select({
            "<script>",
            "select choice_id,product_id,price,storage,choice_img,choice",
            "from product_choice where choice_id in",
            "<foreach item = 'item' index = 'index' collection = 'list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<ProductChoice> seletePrice(@Param("list") List<String> list);

    @Select("select  name from store where id='${store_id}'")
    String slecetStoreName(String store_id);

    @Select("select  name from product where id='${product_id}'")
    String slecetproductName(String product_id);

    @Select("select  id,store_id,name,cover_img,address,high_price,low_price,description,product_params,sales,type,freight,price_range from product where id='${product_id}'")
    Product slecetproduct(String product_id);

    @Select("select store_id, cast(GROUP_CONCAT(choice_id) as char) as choice_ids, CAST(GROUP_CONCAT(number) as char) as numbers from cart_item where user_id='${id}' group by store_id")
    CartItemGroup[] getItemGroups(String id);

}
