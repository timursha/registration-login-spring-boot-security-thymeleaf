package net.javaguides.springboot.dao;

import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.javaguides.springboot.models.Role;
import net.javaguides.springboot.models.User;
import java.util.List;
import java.util.Set;

@Repository("userDao")
public class UserDaoEntityImpl implements UserDao{

    @Autowired
    private UserRepository entityManager;

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.save(user);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        entityManager.deleteById(id);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return entityManager.getOne(id);
    }


    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return entityManager.findByEmail(email);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return entityManager.findAll();
    }

    @Override
    public Set<Role> getRoles(){
        return null;
    }
}

