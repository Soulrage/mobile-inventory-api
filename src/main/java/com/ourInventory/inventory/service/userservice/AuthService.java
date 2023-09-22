package com.ourInventory.inventory.service.userservice;

import com.ourInventory.inventory.dto.JwtRequest;
import com.ourInventory.inventory.dto.JwtResponse;
import com.ourInventory.inventory.dto.RegistrationUserDto;
import com.ourInventory.inventory.dto.UserDTO;
import com.ourInventory.inventory.entity.UserEntity;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Setter
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils utils;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = utils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        UserEntity user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
    }

}
