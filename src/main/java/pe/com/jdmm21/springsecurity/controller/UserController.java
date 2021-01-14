package pe.com.jdmm21.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.jdmm21.springsecurity.model.UserCredentials;
import pe.com.jdmm21.springsecurity.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserCredentials> users() {
        List<UserCredentials> all = userRepository.findAll();
        return all;
    }
}
