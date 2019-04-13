package ro.ccpatrut.authorizationserver.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ccpatrut.authorizationserver.dto.UserDTO;
import ro.ccpatrut.authorizationserver.facades.UserFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "User handler API for spring security projects",
        description = "User handler API that supports basic CRUD operations", tags = {"UserHandler"})
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {
    
    @Autowired
    private UserFacade userFacade;


    @ApiOperation(
            value = "Retrieves all users from the database. Note that the password is hashed",
            response = UserDTO.class, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, responseContainer = "List", response = UserDTO.class, message = "User list retrieved successfully")})
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDTO>> findAll() {
        log.debug("fetching users from the database");
        final List<UserDTO> userDTOList = this.userFacade.findAll();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@Valid @RequestBody final UserDTO userDTO, final UriComponentsBuilder ucBuilder) {
        log.debug("creating new user");
        this.userFacade.save(userDTO);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(userDTO.getUsername()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }


}
