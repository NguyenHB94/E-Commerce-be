package com.tm.j10.web.rest;

import com.tm.j10.domain.Authority;
import com.tm.j10.domain.User;
import com.tm.j10.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/role/{roleName}")
    public ResponseEntity<List<User>> getAllUserByRole(@PathVariable("roleName") String roleName) {
        var ret = this.userRepository.jpqlQueryUserByRole(roleName);
        return ResponseEntity.ok(ret);
    }
}
