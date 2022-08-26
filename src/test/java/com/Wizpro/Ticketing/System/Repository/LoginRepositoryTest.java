package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginRepositoryTest {

    @Autowired
    LoginRepository loginRepository;
    @Test
    public void addLogin()
    {
        Login login=new Login();
        login.setUsername("harshad@gmail.com");
        login.setPassword("1234");
        login.setRole("admin");
        loginRepository.save(login);
    }

}