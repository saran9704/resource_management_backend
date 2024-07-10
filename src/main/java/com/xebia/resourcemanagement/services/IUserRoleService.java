package com.xebia.resourcemanagement.services;

import com.xebia.resourcemanagement.dto.RoleToUserRequest;
import com.xebia.resourcemanagement.dto.UserDto;
import com.xebia.resourcemanagement.model.Role;
import com.xebia.resourcemanagement.model.SystemUser;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
public interface IUserRoleService {

    SystemUser saveUser(UserDto userDto);

    Role saveRole(Role role);

    SystemUser addRoleToUser(RoleToUserRequest roleToUserRequest);


    SystemUser getByEmail(String email);
}
