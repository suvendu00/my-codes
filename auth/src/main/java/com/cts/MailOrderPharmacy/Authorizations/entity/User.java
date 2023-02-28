package com.cts.MailOrderPharmacy.Authorizations.entity;


import javax.persistence.*;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Entity
@Table(name = "User_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fullName;
    private long phno;
    private String email;
    private String userName;
    private String password;
    
}