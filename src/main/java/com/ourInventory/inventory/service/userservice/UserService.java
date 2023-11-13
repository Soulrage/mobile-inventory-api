package com.ourInventory.inventory.service.userservice;

import com.ourInventory.inventory.dto.RegistrationUserDTO;
import com.ourInventory.inventory.entity.UserEntity;
import com.ourInventory.inventory.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Setter(onMethod_ = {@Autowired})
@NoArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public Optional<UserEntity> loadIdByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new User(
                username,
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );

    }

    Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createNewUser(RegistrationUserDTO registrationUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public boolean removeUserByName(String username) {
        long res = userRepository.deleteByUsername(username);
        return res != 0;
    }

}
