package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.TicketStatusAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TicketStatusAuditRepository extends JpaRepository<TicketStatusAudit,Long> {
}
