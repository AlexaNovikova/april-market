package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.april.market.models.Address;
import ru.geekbrains.april.market.models.Role;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.repositories.AddressRepository;
import ru.geekbrains.april.market.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void setContactInfoForUser(User user, String telephone, String email, String address)
    {
        user.setTelephone(telephone);
        user.setEmail(email);
        Address userAddress = new Address();
        userAddress.setAddress(address);
        addressRepository.save(userAddress);
        Collection<Address> userAdresses = user.getAddresses();
        userAdresses.add(userAddress);
        user.setAddresses(userAdresses);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}