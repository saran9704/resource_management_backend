package com.xebia.resourcemanagement.services;

import com.xebia.resourcemanagement.dto.RoleToUserRequest;
import com.xebia.resourcemanagement.dto.UserDto;
import com.xebia.resourcemanagement.model.Role;
import com.xebia.resourcemanagement.model.SystemUser;
import com.xebia.resourcemanagement.repository.RoleRepository;
import com.xebia.resourcemanagement.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserRoleService implements IUserRoleService {
    private final RoleRepository roleRepository;
    private final SystemUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SystemUser saveUser(UserDto userDto) {
        log.info("new user : {} saving ",userDto.getFirstName()+" "+userDto.getLastName());
        SystemUser systemUser=SystemUser.builder()
//                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getEmail())
                .phone(userDto.getPhone())
                .XID(userDto.getXID())
                .designation(userDto.getDesignation())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .department(userDto.getDepartment())
                .isActive(userDto.getIsActive())
                .password(passwordEncoder.encode("welcome@123")).build();

//                .password("welcome@123").build();

        return userRepository.save(systemUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("new role : {} adding",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public SystemUser addRoleToUser(RoleToUserRequest roleToUserRequest) {
        log.info("role:{} assigning to user:{}",roleToUserRequest.getRoleName(),roleToUserRequest.getUsername());

        Optional<Role> byName = roleRepository.findByName(roleToUserRequest.getRoleName());
        Role role=new Role();
        if(byName.isPresent()){
            role=byName.get();
        }
        else {
            throw new IllegalArgumentException("no role found");
        }

        SystemUser byEmail = userRepository.findByUsername(roleToUserRequest.getUsername());
        if (byEmail!=null){
            List<Role> roles = byEmail.getRoles();
            roles.add(role);
            return  userRepository.save(byEmail);
        }
        else {
            throw new IllegalArgumentException("no user found");
        }
    }

    @Override
    public SystemUser getByEmail(String email) {
        SystemUser byUsername = userRepository.findByUsername(email);
        SystemUser user=SystemUser.builder()
                .id(byUsername.getId())
                .email(byUsername.getEmail())
                .username("")
                .phone(byUsername.getPhone())
                .XID(byUsername.getXID())
                .designation(byUsername.getDesignation())
                .firstName(byUsername.getFirstName())
                .lastName(byUsername.getLastName())
                .department(byUsername.getDepartment())
                .isActive(byUsername.getIsActive())
                .roles(byUsername.getRoles())
                .password("").build();

        return user;
    }


}
