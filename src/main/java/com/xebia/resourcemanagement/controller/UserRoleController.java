package com.xebia.resourcemanagement.controller;

import com.xebia.resourcemanagement.dto.ResponseObjectModel;
import com.xebia.resourcemanagement.dto.RoleToUserRequest;
import com.xebia.resourcemanagement.dto.UserDto;
import com.xebia.resourcemanagement.model.Role;
import com.xebia.resourcemanagement.model.SystemUser;
import com.xebia.resourcemanagement.services.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "**",allowedHeaders = "**")
public class UserRoleController {

    @Autowired
    private IUserRoleService iUserRoleService;
    @PostMapping(path = "saveUser")
    public ResponseEntity<ResponseObjectModel> saveUser(@RequestBody UserDto userDto){

        SystemUser user= iUserRoleService.saveUser(userDto);

        return ResponseEntity.ok(
                ResponseObjectModel.builder()
                        .timeStamp(String.valueOf(now()))
                        .data(Map.of("user",user))
                        .message("new user saved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PostMapping(path = "saveRole")
    public ResponseEntity<ResponseObjectModel> saveRole(@RequestBody Role role){
        Role role1= iUserRoleService.saveRole(role);
        return ResponseEntity.ok(
                ResponseObjectModel.builder()
                        .timeStamp(String.valueOf(now()))
                        .data(Map.of("role",role1))
                        .message("new role saved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }
    @PostMapping(path = "addRoleToUser")
    public ResponseEntity<ResponseObjectModel> addRoleToUser(@RequestBody RoleToUserRequest roleToUserRequest){
        SystemUser user= iUserRoleService.addRoleToUser(roleToUserRequest);
        return ResponseEntity.ok(
                ResponseObjectModel.builder()
                        .timeStamp(String.valueOf(now()))
                        .data(Map.of("user",user))
                        .message("new role assigned to user")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping(path = "getCurrentUser")
    public ResponseEntity<ResponseObjectModel> getCurrentUser(@RequestBody String email){
        SystemUser user= iUserRoleService.getByEmail(email);
        return ResponseEntity.ok(
                ResponseObjectModel.builder()
                        .timeStamp(String.valueOf(now()))
                        .data(Map.of("currentUser",user))
                        .message("login successful")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
