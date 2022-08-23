package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.AssignTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AssignTicketRepository extends JpaRepository<AssignTickets,Integer> {
    Optional<AssignTickets> findAllById(int id);

    //SELECT *
    //FROM customers
    //WHERE EXISTS (SELECT *
    //              FROM order_details
    //              WHERE customers.customer_id = order_details.customer_id);
    @Query("SELECT at FROM AssignTickets at WHERE at.user=:id and at.ticket=:tid")
//    @Query(value = "select at.* from assign_tickets at where  ",nativeQuery = true)
    AssignTickets findByAll(int id,int tid);

    List<AssignTickets> findByUser(int id);

    @Transactional
    @Modifying
    @Query("update AssignTickets at set at.user=:user,at.assignby=:assignby where at.id=:id")
    int updateUserSetStatusForName(@Param("id") Integer id,@Param("user") Integer user, @Param("assignby") String assignby);



//    List<AssignTickets>findByUser_idEquals(int id);
}
