package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.TicketStatusAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TicketStatusAuditRepository extends JpaRepository<TicketStatusAudit,Long> {

    @Query("Select t from TicketStatusAudit t where t.customer_id=:id")
    List<TicketStatusAudit> findByCustomer_id(Long id);

    @Query("Select t from TicketStatusAudit t where t.ticket_id=:id")
    TicketStatusAudit findByTicket_id(Long id);
}
