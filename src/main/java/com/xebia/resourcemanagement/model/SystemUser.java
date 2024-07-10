package com.xebia.resourcemanagement.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */
@Entity(name = "system_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "xid")
    private String XID;
    @Column(unique = true,nullable = false)
    private String email;
    private String username;
    private String password;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private Long phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "designation_id")
    private Designation designation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "department_id")
    private Department department;

    private Boolean isActive;
}
