package com.microservices.User.Service.Services;

import org.springframework.http.ResponseEntity;

import com.microservices.User.Service.DTOs.LoginRequestDto;
import com.microservices.User.Service.DTOs.LoginResponseDto;
import com.microservices.User.Service.DTOs.ResponseDto;
import com.microservices.User.Service.DTOs.UserDto;
import com.microservices.User.Service.Entities.User;

public interface UserService {
    
    /**
     * New User Create
     * Unique Contact number
     * @param user
     * @return Response Entity with ResponseDto
     */
    public ResponseEntity<ResponseDto<UserDto>> createUser(User user);

    /**
     * User Login
     * @param loginRequestDto
     * @return Response Entity With ResponseDto
     */
    public ResponseEntity<ResponseDto<LoginResponseDto>> userLogin(LoginRequestDto loginRequestDto);
}
