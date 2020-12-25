package org.orz.psol.config;

import org.orz.psol.model.Path;
import org.orz.psol.model.dbModel.RolePath;
import org.orz.psol.service.RoleService;
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
    RoleService roleService;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<RolePath> rolePaths = roleService.getAllPaths();
        List<Path> paths = new ArrayList<>();
        String url = requestUrl.split("[?]")[0];
        ArrayList<String> roles = new ArrayList<>();
        boolean found = false;
        for (RolePath rp : rolePaths) {

            if (antPathMatcher.match(rp.getPath(),url)) {
                found = true;
                roles.add(rp.getRole());
            }
        }

        String[] str = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            str[i] = roles.get(i);
        }
        if (found)
            return SecurityConfig.createList(str);

        return SecurityConfig.createList("ROLE_ANONYMOUS");
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
