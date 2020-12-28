package org.orz.psol.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.apache.ibatis.annotations.Insert;

public interface InsertMapper extends Mapper {

    @Insert("insert into carousel (url) values ('${url}')")
    int insertIntoCarousel(String url);
}
