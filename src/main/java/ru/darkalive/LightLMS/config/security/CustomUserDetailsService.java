package ru.darkalive.LightLMS.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findFirstByUserName(userName);
        if (user == null) throw new UsernameNotFoundException("Неизвестный пользователь - " + userName);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();
        return userDetails;
    }
}
