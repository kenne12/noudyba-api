package org.kenne.noudybaapi.dto;

import lombok.Data;

@Data
public class UserRoleForm {
    String username;
    private String roleName;
    private String[] roleNames = {};
}
