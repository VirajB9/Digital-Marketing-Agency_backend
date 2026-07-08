package com.viraj.digitalmarketingagencybackend.auth.entity;

import com.viraj.digitalmarketingagencybackend.auth.enmus.PermissionType;
import com.viraj.digitalmarketingagencybackend.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "permission")
public class Permission extends BaseEntity {
    @Id
    private String id;

    private PermissionType permissionType;

    private String module;

    private String description;
}
