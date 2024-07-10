package com.xebia.resourcemanagement.controller;

import com.xebia.resourcemanagement.dto.AuthRequestClass;
import com.xebia.resourcemanagement.dto.ResponseObjectModel;
import com.xebia.resourcemanagement.dto.TokenResponseClass;
import com.xebia.resourcemanagement.dto.UserDto;
import com.xebia.resourcemanagement.model.SystemUser;
import com.xebia.resourcemanagement.services.IUserRoleService;
import com.xebia.resourcemanagement.services.ServiceJWT;
import com.xebia.resourcemanagement.utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AuthController {

    @Autowired
    private UtilityClass utilityClass;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ServiceJWT serviceJWT;
    @Autowired
    private IUserRoleService iUserRoleService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home() {
        return"Welcome";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObjectModel> authenticate(@RequestBody AuthRequestClass requestClass) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestClass.getUsername(),
                            passwordEncoder.encode(requestClass.getPassword())
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = serviceJWT.loadUserByUsername(requestClass.getUsername());

        final String token =
                utilityClass.generateToken(userDetails);

//        return  new TokenResponseClass(token);
        SystemUser user= iUserRoleService.getByEmail(requestClass.getUsername());
        return ResponseEntity.ok(
                ResponseObjectModel.builder()
                        .timeStamp(String.valueOf(now()))
                        .data(Map.of("currentUser",user,"token",token))
                        .message("login successful")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
