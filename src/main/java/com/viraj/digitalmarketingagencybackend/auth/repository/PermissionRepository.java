package com.viraj.digitalmarketingagencybackend.auth.repository;

import com.viraj.digitalmarketingagencybackend.auth.enmus.PermissionType;
import com.viraj.digitalmarketingagencybackend.auth.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PermissionRepository extends MongoRepository<Permission, String> {

    boolean existsByPermissionType(PermissionType permissionType);
}
