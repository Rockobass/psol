package org.orz.psol.config;

import org.orz.psol.model.Path;
import org.orz.psol.model.RolePath;
import org.orz.psol.service.impl.RolePathServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    RolePathServiceImpl rolePathService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<RolePath> rolePaths = rolePathService.list();
        List<Path> paths = new ArrayList<>();

        // 获得路径和权限的映射列表
        for (RolePath rp : rolePaths) {
            boolean found = false;
            for (Path path : paths) {
                if (rp.getUrl().equals(path.getUrl())) {
                    found = true;
                    path.addRole(rp.getRole());
                    break;
                }
            }
            if (!found) {
                Path p = new Path();
                p.setUrl(rp.getUrl());
                List<String> roles = new ArrayList<>();
                p.setRoles(roles);
                p.addRole(rp.getRole());
                paths.add(p);
            }
        }

        // 返回可以访问请求路径的 角色列表
        for (Path path : paths) {
            if (antPathMatcher.match(path.getUrl(), requestUrl)) {
                List<String> roles = path.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i);
                }
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
