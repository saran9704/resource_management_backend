package com.xebia.resourcemanagement.repository;

import com.xebia.resourcemanagement.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author saran saravanan
 * @version 1.0
 * @since 02/06/2023
 */

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

   SystemUser findByUsername(String email);
}