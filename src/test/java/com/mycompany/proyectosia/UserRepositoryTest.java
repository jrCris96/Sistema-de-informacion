package com.mycompany.proyectosia;


import com.mycompany.proyectosia.user.User;
import com.mycompany.proyectosia.user.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user= new User();
        user.setEmail("cristianquispech@gmail.com");
        user.setPassword("456");
        user.setNombre("Javier");
        user.setApellido("Choque");

        User savedUser= repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void tstUpdate(){
        Integer userId=1;
        Optional<User> optionalUser = repo.findById(userId);
        User user= optionalUser.get();
        user.setPassword("123");
        repo.save(user);

        User updatedUser= repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("123");
    }

    @Test
    public void testGet(){
        Integer userId=1;
        Optional<User> optionalUser= repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId=1;
        repo.deleteById(userId);
        Optional<User> optionalUser= repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
