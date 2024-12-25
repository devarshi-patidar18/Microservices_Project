package com.microservices.User.Service.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.microservices.User.Service.Config.SecurityConfig;
import com.microservices.User.Service.DTOs.LoginRequestDto;
import com.microservices.User.Service.DTOs.LoginResponseDto;
import com.microservices.User.Service.DTOs.ResponseDto;
import com.microservices.User.Service.DTOs.UserDto;
import com.microservices.User.Service.Entities.User;
import com.microservices.User.Service.JWT.JwtHelper;
import com.microservices.User.Service.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * New User Create
     * Unique Contact number
     * @param user
     * @return Response Entity with UserDto
     */
    @Override
    public ResponseEntity<ResponseDto<UserDto>> createUser(User user) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        if(!userRepository.existsByContact(user.getContact())){
            user.setId(null); /* Id always null ensures that always new user will be created */
            user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(user, UserDto.class);
            responseDto.setData(userDto);
            responseDto.setMessage("User Created Successfully!");
            responseDto.setStatuscode(200);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
        else{
            responseDto.setMessage("User Already Exists, Please Try with different contact number!!");
            responseDto.setStatuscode(200);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<LoginResponseDto>> userLogin(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getContact(), loginRequestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getContact());
        String token = jwtHelper.generateToken(userDetails.getUsername());
        LoginResponseDto loginResponseDto = new LoginResponseDto(userDetails.getUsername(),token);
        if(userDetails.getUsername().isEmpty()){
            return new ResponseEntity<>(new ResponseDto<LoginResponseDto>("Bad Credentials!",200,null),HttpStatus.OK);
        }
        
        return new ResponseEntity<>(new ResponseDto<LoginResponseDto>("LoggedIn Successfully",200,loginResponseDto),HttpStatus.OK);
    }

}
