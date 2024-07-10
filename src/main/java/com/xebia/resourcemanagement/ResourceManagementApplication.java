package com.xebia.resourcemanagement;

import com.xebia.resourcemanagement.dto.RoleToUserRequest;
import com.xebia.resourcemanagement.dto.UserDto;
import com.xebia.resourcemanagement.model.Department;
import com.xebia.resourcemanagement.model.Designation;
import com.xebia.resourcemanagement.model.Role;
import com.xebia.resourcemanagement.model.SystemUser;
import com.xebia.resourcemanagement.repository.SystemUserRepository;
import com.xebia.resourcemanagement.services.IUserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */

@SpringBootApplication
public class ResourceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceManagementApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}



	@Bean
	public CommandLineRunner loadData(IUserRoleService iUserRoleService){
		return args -> {
			UserDto userDto= UserDto.builder()
					.email("admin@admin.com")
					.phone(9712653723L)
					.XID("XID1297")
					.designation(new Designation(null,"Developer",true))
					.firstName("admin")
					.lastName("system")
					.department(new Department(null,"Xebia_NDS",true))
					.isActive(true)
					.build();
			SystemUser user = iUserRoleService.saveUser(userDto);

			Role role=new Role(null,"ROLE_ADMIN",true);
			Role role2=new Role(null,"ROLE_USER",true);
			iUserRoleService.saveRole(role);
			iUserRoleService.saveRole(role2);

			RoleToUserRequest request=new RoleToUserRequest("admin@admin.com","ROLE_ADMIN");
			RoleToUserRequest request2=new RoleToUserRequest("admin@admin.com","ROLE_USER");
			iUserRoleService.addRoleToUser(request);
			iUserRoleService.addRoleToUser(request2);
		};
	}
}
