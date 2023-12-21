package com.application.blog.velvetvoices.model.role;

import com.application.blog.velvetvoices.model.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description", length = 1000, nullable = true, unique = false, columnDefinition = "TEXT")
    private String roleDescription;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonBackReference(value = "role_user")
    private List<User> users;
}
