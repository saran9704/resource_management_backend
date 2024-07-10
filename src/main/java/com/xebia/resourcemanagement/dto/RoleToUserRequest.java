package com.xebia.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleToUserRequest {
    private String username;
    private String RoleName;
}
