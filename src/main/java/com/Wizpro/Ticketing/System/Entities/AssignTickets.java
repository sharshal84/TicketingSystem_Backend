package com.Wizpro.Ticketing.System.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class AssignTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int user;
    private int ticket;
    private String assignby;


    public AssignTickets()
    {

    }

    public AssignTickets(int id, int user_id, int ticket_id, String assign_by) {
        this.id = id;
        this.user= user_id;
        this.ticket= ticket_id;
        this.assignby = assign_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user;
    }

    public void setUser_id(int user_id) {
        this.user= user_id;
    }

    public int getTicket_id() {
        return ticket;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket= ticket_id;
    }

    public String getAssign_by() {
        return assignby;
    }
    public void setAssign_by(String assign_by) {
        this.assignby = assign_by;
    }
    @Override
    public String toString() {
        return "AssignTickets{" +
                "id=" + id +
                ", user_id=" + user +
                ", ticket_id=" + ticket +
                ", assign_by='" + assignby + '\'' +
                '}';
    }
}
