package org.orz.psol.service;

import org.orz.psol.model.RolePath;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 配置相应权限可访问的路径 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-12-03
 */
public interface RolePathService extends IService<RolePath> {

    List<RolePath> getAllPaths();
}
