package com.microservices.User.Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.microservices.User.Service.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    boolean existsByContact(String contact);

    UserDetails findByContact(String username);

}
