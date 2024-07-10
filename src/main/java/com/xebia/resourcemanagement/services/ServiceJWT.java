package com.xebia.resourcemanagement.services;

import com.xebia.resourcemanagement.model.SystemUser;
import com.xebia.resourcemanagement.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */

@Service
public class ServiceJWT implements UserDetailsService {

    @Autowired
    SystemUserRepository repositoryInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        SystemUser byUsername = repositoryInterface.findByUsername(username);

        if(byUsername!=null){
            return new User(byUsername.getUsername(),byUsername.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("User Not Fund");

    }


}
