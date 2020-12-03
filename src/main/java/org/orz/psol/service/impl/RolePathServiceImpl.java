package org.orz.psol.service.impl;

import org.orz.psol.model.RolePath;
import org.orz.psol.mapper.RolePathMapper;
import org.orz.psol.service.RolePathService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 配置相应权限可访问的路径 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-12-03
 */
@Service
public class RolePathServiceImpl extends ServiceImpl<RolePathMapper, RolePath> implements RolePathService {
    @Autowired
    RolePathMapper mapper;
    @Override
    public List<RolePath> getAllPaths() {
        return mapper.selectByMap(null);
    }
}
