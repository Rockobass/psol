package org.orz.psol.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Path {
    String url;
    List<String> roles;

    public void addRole(String role) {
        this.roles.add(role);
    }
}
