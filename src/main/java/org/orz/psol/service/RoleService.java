package org.orz.psol.service;

import org.orz.psol.mapper.RoleMapper;
import org.orz.psol.model.dbModel.RolePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<RolePath> getAllPaths(){
        return roleMapper.getAllPaths();
    }
}
