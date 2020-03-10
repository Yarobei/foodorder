package by.it.academy.foodorder.parent.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Long userId;

    @Column
    private String username;

    @Column
    @ManyToMany
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Set<Role> roles;

    @Column
    private String password;

    @Transient
    @Column(name = "password_confirm")
    private String passwordConfirm;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Basket basket;
}
