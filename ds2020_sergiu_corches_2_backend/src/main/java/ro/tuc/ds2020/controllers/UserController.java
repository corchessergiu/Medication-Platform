package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ro.tuc.ds2020.dtos.UserDataDTO;
import ro.tuc.ds2020.dtos.builders.UserDataBuilder;
import ro.tuc.ds2020.entities.UserData;

import ro.tuc.ds2020.services.UserDataService;

import java.security.Principal;


@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {


    private final UserDataService userDataService;

    @Autowired
    public UserController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }



    @GetMapping(value = "/loggedUser")
    public ResponseEntity<UserDataDTO> getLoggedUser(Principal principal) {
        return new ResponseEntity<>(UserDataBuilder.toUserDataDTO(userDataService.findUserByUsername(principal.getName())), null, HttpStatus.OK);

    }

    //TODO: UPDATE, DELETE per resource

}
