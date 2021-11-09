package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.april.market.dtos.UserDto;
import ru.geekbrains.april.market.models.Role;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.repositories.RoleRepository;
import ru.geekbrains.april.market.repositories.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Transactional
    public User register(User user){
        int workload =12;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashed_password);
        Role role = roleRepository.getOne(1L);
        user.setRoles(Collections.singleton(role));
        user = userRepository.save(user);
        return user;
    }
}
