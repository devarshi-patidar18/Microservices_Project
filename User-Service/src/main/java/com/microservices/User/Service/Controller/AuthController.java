package com.microservices.User.Service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.User.Service.DTOs.LoginRequestDto;
import com.microservices.User.Service.DTOs.LoginResponseDto;
import com.microservices.User.Service.DTOs.ResponseDto;
import com.microservices.User.Service.DTOs.UserDto;
import com.microservices.User.Service.Entities.User;
import com.microservices.User.Service.Services.UserService;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST,value = "/signup")
    private ResponseEntity<ResponseDto<UserDto>> userCreate(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    private ResponseEntity<ResponseDto<LoginResponseDto>> userLogin(@RequestBody LoginRequestDto loginRequestDto){
        return userService.userLogin(loginRequestDto);
    }

    @PostMapping("/edit")
    private ResponseEntity<ResponseDto<User>> userUpdate(@RequestBody User user){
        System.out.println("Edit works +++++++++++++++++++++++++++++==");
        ResponseDto<User> responseDto = new ResponseDto<>();
        responseDto.setData(user);
        responseDto.setMessage("User Updated Successfully!");
        responseDto.setStatuscode(200);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
