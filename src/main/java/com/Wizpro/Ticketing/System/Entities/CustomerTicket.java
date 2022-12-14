package com.Wizpro.Ticketing.System.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "customer_ticket")
@Component
public class CustomerTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @CreationTimestamp
    private LocalDateTime created_at;

    private String serialnumber;
    private String remark;
    private String status;
    private String filename;
    private String filetype;
    private String assign;
    private String location;

    @Nullable
    private int customer;
    @Lob
    private byte[] filedata;

    private int product;

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public CustomerTicket(Long id, String description,String location, LocalDateTime created_at, String serialnumber, String remark, String status, String filename, String filetype, String assign, byte[] filedata, int product, int customer) {
        this.id = id;
        this.description = description;
        this.created_at = created_at;
        this.serialnumber = serialnumber;
        this.remark = remark;
        this.status = status;
        this.filename = filename;
        this.filetype = filetype;
        this.assign = assign;
        this.filedata = filedata;
        this.product = product;
        this.customer = customer;
        this.location=location;
    }

    public CustomerTicket(String description,String location, String serialnumber, String remark, String status, String originalFilename, String contentType, byte[] bytes, int product, int customer) {
    this.description=description;
    this.serialnumber=serialnumber;
    this.remark=remark;
    this.status=status;
    this.filename=originalFilename;
    this.filetype=contentType;
    this.filedata=bytes;
    this.product=product;
    this.customer=customer;
    this.location=location;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    //    @JsonIgnore
//    @OneToOne(cascade = CascadeType.DETACH)
//    @JoinColumn(name = "p_id",referencedColumnName = "id")
//    private Product customer_product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime  created_at) {
        this.created_at = created_at;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

//    public Product getCustomer_product() {
//        return customer_product;
//    }
//    public void setCustomer_product(Product customer_product) {
//        this.customer_product = customer_product;
//    }
    public CustomerTicket()
    {

    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }
}
