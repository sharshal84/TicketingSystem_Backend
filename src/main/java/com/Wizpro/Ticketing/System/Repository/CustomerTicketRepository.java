package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.CustomerTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerTicketRepository extends JpaRepository<CustomerTicket,Long> {

    @Query(value = "select t.*,p.product from customer_ticket t inner join product p where t.product=p.id",nativeQuery = true)
    List<CustomerTicket> findAllTickets();
//
    @Query(value = "Select * from CustomerTicket t where t.id=:id",nativeQuery = true)
    CustomerTicket findByFile(@Param("id") Integer id);

    @Query(nativeQuery = true,value = "select t.*,p.product,at.assignby from customer_ticket t inner join product p on t.product=p.id inner join assign_tickets at on at.ticket=t.id where at.user=:id")
//    @Query(value = "select t.*,p.product from user u inner join user_product up on u.id=up.user_id inner join product p on p.id=up.product_id inner join customer_ticket t on t.product=p.id where u.id=:id order by t.id",nativeQuery = true)
    List<CustomerTicket> findUserTickets(@Param("id")int id);
//
    @Query(value = "select t.*,p.product from customer_ticket t inner join product p on t.p_id=p.id inner join  customer c on t.customer=c.id where c.email=:username",nativeQuery = true)
    List<CustomerTicket> findCustomerTickets(@Param("username") String username);

    @Query("Select ct From CustomerTicket ct where ct.customer=:customer")
    List<CustomerTicket> findByCustomerId(int customer);

//    @Modifying
//    @Query(
//            value = "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//            nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//                    @Param("status") Integer status, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "insert into customer_ticket(description,serialnumber,remark,status,filename,filetype,filedata,product,customer) values (:description,:serialnumber,:remark,:status,:filename,:filetype,:filedata,:product,:customer)")
    void insertTickets(@Param("description")String description,@Param("serialnumber") String serialnumber,@Param("remark")String remark,@Param("status")String status,@Param("filename")String filename,@Param("filetype") String filetype,@Param("filedata")byte[] filedata,@Param("product") int product,@Param("customer")int customer);


    @Transactional
    @Modifying
    @Query("update CustomerTicket ct set ct.assign=:name where ct.id=:tid")
    Integer updateAssign(@Param("tid")Long tid, String name);


    Long countByStatus(String status);
}
