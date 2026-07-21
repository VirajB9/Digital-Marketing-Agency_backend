package com.viraj.digitalmarketingagencybackend.auth.repository;

import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(String name);

}
