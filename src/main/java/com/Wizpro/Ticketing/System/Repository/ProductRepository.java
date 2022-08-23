package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.Login;
import com.Wizpro.Ticketing.System.Entities.Product;
import com.Wizpro.Ticketing.System.Entities.User_Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select p.product,p.id from product p",nativeQuery = true)
    List<Product> findAllProduct();

    @Query("SELECT p FROM Product p WHERE p.product = :name")
    Product findProductByUserproduct(String name);

    @Query(nativeQuery = true,value = "select user_id from user_product where product_id=:id")
    List<User_Product>findUsersByProduct(int id);

}
