package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS_ROLES")
public class UserRoleEntity {

    @Id
    @Column("USERS_ROLE_ID")
    private int users_role_id;

    @Column("ROLE_ID")
    private int role_id;

    @Column("USER_ID")
    private int user_id;

    public UserRoleEntity(int users_role_id, int role_id, int user_id) {
        this.users_role_id = users_role_id;
        this.role_id = role_id;
        this.user_id = user_id;
    }

    public int getUsers_role_id() {
        return users_role_id;
    }

    public void setUsers_role_id(int users_role_id) {
        this.users_role_id = users_role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
