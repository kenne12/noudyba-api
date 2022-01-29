package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {

    @Id
    private Integer id;
    @NotEmpty(message = "Role Name can not be empty or null")
    private String nom;
}
