package com.Wizpro.Ticketing.System.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "login")
@Component
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Login()
    {

    }

    public Login(int id, String username, String password, String role,Integer customer,Integer user) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.customer_id=customer;
        this.user_id=user;
    }

//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @Column(name = "user_id")
    private Integer user_id;

//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @Column(name = "customer_id")
    private Integer customer_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUser() {
        return user_id;
    }

    public void setUser(Integer user) {
        this.user_id = user;
    }

    public Integer getCustomer() {
        return customer_id;
    }

    public void setCustomer(Integer customer) {
        this.customer_id = customer;
    }


    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user_id +
                ", customer=" + customer_id +
                '}';
    }
//    private String role;
}
