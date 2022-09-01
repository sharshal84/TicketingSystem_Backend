package com.Wizpro.Ticketing.System.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Component
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int ticket_id;
    private int user_id;
    private int customer_id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    private String message;
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", ticket_id=" + ticket_id +
                ", user_id=" + user_id +
                ", customer_id=" + customer_id +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }



    public Comment()
    {

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Comment(Long id, int ticket_id, int user_id, int customer_id, LocalDateTime timestamp, String message) {
        this.id = id;
        this.ticket_id = ticket_id;
        this.user_id = user_id;
        this.customer_id = customer_id;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
