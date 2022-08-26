package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.User;
import com.Wizpro.Ticketing.System.Entities.User_Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void findEnrollProductBy() {
        List<User> list=userRepository.findAll();
        System.out.println(list);
    }
    @Test
    public void getUsers()
    {
//        return userRepository.findAll();
//        List<User>list=userRepository.findAll();
        List<User>list=userRepository.findUserAndProduct();
        for (User u:list) {
            System.out.println(u.getEnrollProduct());
        }
        System.out.println(list);
//        return userRepository.findUserAndProduct();
    }
}