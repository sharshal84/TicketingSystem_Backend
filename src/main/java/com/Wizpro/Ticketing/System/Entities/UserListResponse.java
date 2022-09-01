package com.Wizpro.Ticketing.System.Entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserListResponse {

    private int key;
    private String label;
    private int value;
}
