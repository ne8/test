package ro.ccpatrut.authorizationserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "DTO for used for user operations")
@Getter
@Setter
public class UserDTO {


    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @ApiModelProperty(required = true)
    private String email;

    @NotNull
    @Size(min = 6, max = 255)
    @ApiModelProperty(required = true)
    private String username;

    @NotNull
    @Size(min = 2, max = 255)
    @ApiModelProperty(required = true)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255)
    @ApiModelProperty(required = true)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 255)
    @ApiModelProperty(required = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private Date creationDate;

    @ApiModelProperty(hidden = true)
    private String type;

    @ApiModelProperty(hidden = true)
    private String state;

    @ApiModelProperty(hidden = true)
    private boolean isAccountNonExpired;

    @ApiModelProperty(hidden = true)
    private boolean isAccountNonLocked;

    @ApiModelProperty(hidden = true)
    private boolean isCredentialsNonExpired;

    @ApiModelProperty(hidden = true)
    private boolean isEnabled;


}
