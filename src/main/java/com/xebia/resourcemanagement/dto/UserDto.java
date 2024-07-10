package com.xebia.resourcemanagement.dto;

import com.xebia.resourcemanagement.model.Department;
import com.xebia.resourcemanagement.model.Designation;
import com.xebia.resourcemanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Validated
public class UserDto {
//    private Long id;
    private String XID;
    private String email;
    private String firstName;
    private String lastName;
    private Long phone;
    private Designation designation;
    private Department department;
    private Boolean isActive;
}
