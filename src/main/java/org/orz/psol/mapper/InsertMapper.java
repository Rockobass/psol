package org.orz.psol.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface InsertMapper extends Mapper {

    @Insert("insert into carousel (url) values ('${url}')")
    int insertIntoCarousel(String url);

    @Select("select store_id from product group by store_id")
    String[] getAllStoreId();
}
