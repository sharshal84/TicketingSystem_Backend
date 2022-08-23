package com.Wizpro.Ticketing.System.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Component
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String product;

    public Product()
    {

    }

    public Product(int id, String product) {
        this.id = id;
        this.product = product;
    }

//    @JsonIgnore
//    @OneToOne(fetch =FetchType.LAZY,mappedBy = "customer_product",cascade = CascadeType.DETACH)
//    private CustomerTicket ticket;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrollProduct")
    private List<User> users=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

//    public CustomerTicket getTicket() {
//        return ticket;
//    }
//
//    public void setTicket(CustomerTicket ticket) {
//        this.ticket = ticket;
//    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
