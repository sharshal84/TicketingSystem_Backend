package com.Wizpro.Ticketing.System.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;
//    private int ticket_id;
    private String user;
    private String customer;
    private String timestamp;
    private String message;
    private String role;
}
