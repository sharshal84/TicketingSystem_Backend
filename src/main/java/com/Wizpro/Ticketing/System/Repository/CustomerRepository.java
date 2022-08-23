package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(nativeQuery = true,value = "select * from customer c where c.email=:cust_name")
    Customer findByName(@Param("cust_name")String cust_name);

    Optional<Customer> findByEmail(String username);

}
