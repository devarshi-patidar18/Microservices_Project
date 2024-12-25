package com.microservices.User.Service.DTOs;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.microservices.User.Service.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    private String name;
    private String lastname;
    private String city;
    private String state;
    private String country;
    private String contact;
    private String email;
    private Date createddate;
    private Date modifieddate;
    private Set<Role> roles = new HashSet<>();
    private Boolean isactive;

}
