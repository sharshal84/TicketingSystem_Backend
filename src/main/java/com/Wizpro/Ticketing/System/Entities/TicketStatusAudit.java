package com.Wizpro.Ticketing.System.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TicketStatusAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ticket_id;
    private Long customer_id;
    private Long user_id;
    private String dummy_status;
    @CreationTimestamp
    private LocalDateTime dummy_created_at;

    private String updated_status;

    @UpdateTimestamp
    private LocalDateTime updated_created_at;

    public TicketStatusAudit()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getDummy_status() {
        return dummy_status;
    }

    public void setDummy_status(String dummy_status) {
        this.dummy_status = dummy_status;
    }

    public LocalDateTime getDummy_created_at() {
        return dummy_created_at;
    }

    public void setDummy_created_at(LocalDateTime dummy_created_at) {
        this.dummy_created_at = dummy_created_at;
    }

    public String getUpdated_status() {
        return updated_status;
    }

    public void setUpdated_status(String updated_status) {
        this.updated_status = updated_status;
    }

    public LocalDateTime getUpdated_created_at() {
        return updated_created_at;
    }

    public void setUpdated_created_at(LocalDateTime updated_created_at) {
        this.updated_created_at = updated_created_at;
    }

    public TicketStatusAudit(Long id, Long ticket_id, Long customer_id, Long user_id, String dummy_status, LocalDateTime dummy_created_at, String updated_status, LocalDateTime updated_created_at) {
        this.id=id;
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.dummy_status = dummy_status;
        this.dummy_created_at = dummy_created_at;
        this.updated_status = updated_status;
        this.updated_created_at = updated_created_at;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
