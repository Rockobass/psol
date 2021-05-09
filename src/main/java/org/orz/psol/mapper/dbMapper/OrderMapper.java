package org.orz.psol.mapper.dbMapper;

import org.apache.ibatis.annotations.Insert;
import org.orz.psol.model.dbModel.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Date;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-01-06
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Insert("INSERT INTO `order` ( id, user_id, store_id, status, price, order_time ) VALUES ( '${id}', '${userId}', '${storeId}', '${status}', ${price}, ${time} )")
    void create(String id, String userId, String storeId, String status, float price, String time);
}
