package com.example.springsecurity2.DAO;


import com.example.springsecurity2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    @Query("SELECT distinct u from User u JOIN fetch u.roles where u.id= :id")
    User findUserById(@Param("id") Long id);

    User findDistinctByUsername(String username);
    void deleteById(Long id);
}
