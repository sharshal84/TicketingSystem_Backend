package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.User;
import com.Wizpro.Ticketing.System.Entities.UserResponse;
import com.Wizpro.Ticketing.System.Entities.User_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query(value = "select u.id,u.email,u.name,u.role,u.password,p.product from user as u join user_product up on up.user_id=u.id join product p on p.id=up.product_id group by email order by id",nativeQuery = true)
    List<User> findUserAndProduct();
    @Query(value = "select u.*,p.product from user u inner join user_product up on u.id=up.user_id inner join product p on p.id=up.product_id",nativeQuery = true)
    List<User> findProduct();

    User findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from user except select * from user u where u.email=:name")
    List<User>findAllUserByName(String name);

    @Query(value = " select user_id from user_product up inner join user u on up.user_id=u.id where product_id=:id",nativeQuery = true)
    List findEnrollProductBy(int id);
//    @Query("SELECT u FROM auth_user as u WHERE u.isEnabled AND u.id IN"
//            + " (SELECT r.user_id FROM user_role as r WHERE r.role_id = ?1)")
//    public List<OAuthUser> findByRole(int roleID);

}
