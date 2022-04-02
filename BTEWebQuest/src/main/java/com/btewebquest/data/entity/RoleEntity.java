package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing a Role object
 *
 * @author sfradet
 * @version 1.0
 */
@Table("ROLES")
public class RoleEntity {

    @Id
    @Column("ROLE_ID")
    private Integer id;

    @Column("ROLE_NAME")
    private String name;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
