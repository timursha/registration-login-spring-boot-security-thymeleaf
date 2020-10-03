package net.javaguides.springboot.service;

import net.javaguides.springboot.models.Role;

import java.util.Set;

public class RoleService {
    private Set<Role> roles;

    public RoleService(Set<Role> roles){
        this.roles = roles;
    }

    public RoleService() { }

    public void addRole(Role roles){
        this.roles.add(roles);
    }
}
