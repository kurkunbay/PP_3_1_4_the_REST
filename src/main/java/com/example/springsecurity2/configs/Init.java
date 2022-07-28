package com.example.springsecurity2.configs;

import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;

@Component
@Transactional
public class Init {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public Init(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @PostConstruct
    @Transactional
    public void postConstruct() {
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("$2a$12$sn9KvEVkIANLssoCvEnh0.XqIxsE3BwaLt5qSltxaOj11eQoLCj8i"); //Password: user
        admin.setFirstName("Ivan");
        admin.setLastName("Ivanov");
        admin.setAge(26);

        User user = new User();
        user.setEmail("user");
        user.setPassword("$2a$12$sn9KvEVkIANLssoCvEnh0.XqIxsE3BwaLt5qSltxaOj11eQoLCj8i"); //Password: user
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setAge(20);

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Role role2 = new Role();
        role2.setName("ROLE_USER");

        admin.setRoles(Collections.singleton(role));
        user.setRoles(Collections.singleton(role2));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.persist(admin);
        entityManager.persist(role);
        entityManager.persist(role2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
