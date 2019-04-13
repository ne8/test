package ro.ccpatrut.authorizationserver.entities;


import lombok.Getter;
import lombok.Setter;
import ro.ccpatrut.authorizationserver.entities.enums.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users", schema = "users")
@Getter
@Setter
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "state", nullable = false)
    private final String state = State.ACTIVE.getState();

    @Column(name = "creation_date", nullable = false)
    private final Date creationDate = new Date();

    @Column(name = "is_account_non_expired", nullable = false)
    private final boolean isAccountNonExpired = true;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private final boolean isCredentialsNonExpired = true;

    @Column(name = "is_account_non_locked")
    private final boolean accountNonLocked = true;


    //TODO:  improve so you don't have duplicates within the database
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(schema = "users", name = "user_user_profile",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_profile_id")}
    )
    private Set<UserRoleEntity> userRoles;


}
