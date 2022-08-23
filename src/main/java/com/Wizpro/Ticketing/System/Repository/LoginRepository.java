package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.Login;
import com.Wizpro.Ticketing.System.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface LoginRepository extends JpaRepository<Login,Integer> {

    Login findByUsernameAndPassword(String username,String password);

    @Query(nativeQuery = true,value = "select l.user_id,l.id,l.role,l.username,l.password,l.customer_id from login l where l.username=:username and l.password=:password")
    Login findAllByName(@Param("username")String username,@Param("password") String password);

}
