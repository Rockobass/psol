package org.orz.psol.service.impl;

import org.orz.psol.model.Admin;
import org.orz.psol.mapper.AdminMapper;
import org.orz.psol.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员对象 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-12-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
