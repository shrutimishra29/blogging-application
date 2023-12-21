package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.model.User.MyUserDetails;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userByUserEmail = userRepository.findUserByUserEmail(email);
        if(userByUserEmail == null){
            throw new UsernameNotFoundException("User with email : " + email + " doesn't exit");
        }

        return new MyUserDetails(userByUserEmail);
    }
}
