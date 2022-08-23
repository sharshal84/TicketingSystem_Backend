package com.Wizpro.Ticketing.System.Entities;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TicketResponse {

    private Long id;
    private String description;
    private String  created_at;
    private String serialnumber;
    private String remark;
    private String status;
    private String file;
    private String  customer_product;

    private String assignby;

    @Override
    public String toString() {
        return "TicketResponse{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created_at='" + created_at + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", file='" + file + '\'' +
                ", customer_product='" + customer_product + '\'' +
                ", assignby='" + assignby + '\'' +
                ", customer_name='" + customer_name + '\'' +
                '}';
    }

    public String getAssignby() {
        return assignby;
    }

    public void setAssignby(String assignby) {
        this.assignby = assignby;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    private String customer_name;

    public TicketResponse() {
    }

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCustomer_product() {
        return customer_product;
    }

    public void setCustomer_product(String customer_product) {
        this.customer_product = customer_product;
    }
}
