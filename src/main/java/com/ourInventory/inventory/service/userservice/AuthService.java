package com.ourInventory.inventory.service.userservice;

import com.ourInventory.inventory.dto.JwtRequest;
import com.ourInventory.inventory.dto.JwtResponse;
import com.ourInventory.inventory.dto.RegistrationUserDto;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Setter
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils utils;

    @Autowired
    AuthenticationManager authenticationManager;

    public Optional<JwtResponse> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return Optional.empty();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = utils.generateToken(userDetails);
        return Optional.of(new JwtResponse(token));
    }

    public boolean createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return false;
        }
        userService.createNewUser(registrationUserDto);
        return true;
    }

    public boolean removeUser(String userName) {
        return userService.removeUserByName(userName);

    }

}
