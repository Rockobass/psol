package org.orz.psol.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.apache.ibatis.annotations.Select;
import org.orz.psol.model.dbModel.RolePath;

import java.util.List;

public interface RoleMapper extends Mapper {

    @Select("select * from role_path")
    List<RolePath> getAllPaths();
}
