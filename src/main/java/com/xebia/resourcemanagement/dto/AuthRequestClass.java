package com.xebia.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestClass {
    private String username;
    private String password;
}
