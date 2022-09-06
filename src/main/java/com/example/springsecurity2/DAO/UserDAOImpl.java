package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("select distinct u from User as u join fetch u.roles", User.class).getResultList();
        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT users FROM User users join fetch users.roles WHERE users.id = :id", User.class);
        return query
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT user FROM User user WHERE user.email = :name", User.class);
        return query
                .setParameter("name", email)
                .getSingleResult();
    }
}
