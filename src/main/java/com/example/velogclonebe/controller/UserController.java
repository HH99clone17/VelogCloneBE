package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }

    // user, manager, admin 권한 접근 가능
    @GetMapping("/api/v1/user")
    public String user() {
        return "user";
    }

    // manager, admin 권한 접근 가능
    // @GetMapping("/api/v1/manager")
    // public String manager() {
    //     return "manager";
    // }

    // admin 권한 접근 가능
    // @GetMapping("/api/v1/admin")
    // public String admin() {
    //     return "admin";
    // }
}
