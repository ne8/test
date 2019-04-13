package ro.ccpatrut.authorizationserver.entities;

import lombok.Getter;
import lombok.Setter;
import ro.ccpatrut.authorizationserver.entities.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "users")
@Getter
@Setter
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", length = 64, unique = true, nullable = false)
    private final String type = UserRoleEnum.USER.getUserRole();

    public String getType() {
        return this.type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


}
