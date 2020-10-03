package net.javaguides.springboot.dao;


import net.javaguides.springboot.models.Role;

import java.util.List;

public interface RoleDao {
    void createRole(List<Role> roles);
    List<Role> getAllRoles();
}