package org.orz.psol.mapper.dbMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Select;
import org.orz.psol.model.VO.DisplayProduct;
import org.orz.psol.model.dbModel.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-12-28
 */
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select id, cover_img, name, high_price, low_price, sales from product ORDER BY RAND() limit 5")
    DisplayProduct[] getRandomFive();

    @Select("select * from product where id='${id}'")
    Product getProductById(String id);

    @Select("select name from store where id='${pid}'")
    String getStoreNameByStoreId(String pid);


    @Select("select service_id from product_service where product_id='${id}'")
    int[] getServiceIdByPID(String id);

    @Select("select url from product_imgs where is_swipper=1 and product_id='${id}'")
    String[] getSwippersByPID(String id);
    @Select("select url from product_imgs where is_swipper=0 and product_id='${id}'")
    String[] getBodyImgByPID(String id);
}
