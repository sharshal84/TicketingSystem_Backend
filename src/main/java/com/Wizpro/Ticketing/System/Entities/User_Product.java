package com.Wizpro.Ticketing.System.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Product {
    private int user_id;
    private int product_id;
}
