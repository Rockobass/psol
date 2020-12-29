package org.orz.psol.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.apache.ibatis.annotations.Select;

public interface HomeMapper extends Mapper {

    @Select("select url from carousel ORDER BY RAND() limit 6")
    String[] getSwipperUrls();
}
