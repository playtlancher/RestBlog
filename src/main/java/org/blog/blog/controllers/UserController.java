package org.blog.blog.controllers;


import lombok.AllArgsConstructor;
import org.blog.blog.dto.UserDTO;
import org.blog.blog.servises.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> handleLogin(@RequestBody UserDTO userDTO) {
        return mappingResponse(userService.login(userDTO));
    }

    @GetMapping("/registration")
    public ResponseEntity<String> showRegistrationForm() {
        return new ResponseEntity<>("Registration form goes here", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> showLoginForm() {
        return new ResponseEntity<>("Login form goes here", HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> handleRegistration(@RequestBody UserDTO userDTO) {
        return mappingResponse(userService.registration(userDTO));
    }


    private ResponseEntity<UserDTO> mappingResponse(UserDTO userDTO) {
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
