package net.javaguides.springboot.service;

import net.javaguides.springboot.models.Role;
import net.javaguides.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void updateUser(User user);

    void removeUserById(Long id);

    User getUserById(Long id);

    List<User> listUsers();

    void saveRoles(List<Role> roles);

    List<Role> getAllRoles();
}
