package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.dto.response.ArticleListResponseDto;
import com.example.velogclonebe.domain.dto.response.UserInfoResponseDto;
import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    @PostMapping("join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }

    @GetMapping("/api/user")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return userService.getUserInfo(username);
    }

    // TEST - user, manager, admin 권한 접근 가능
    @GetMapping("/api/v1/user")
    public String user() {
        return "user";
    }

    // TEST - manager, admin 권한 접근 가능
    // @GetMapping("/api/v1/manager")
    // public String manager() {
    //     return "manager";
    // }

    // TEST - admin 권한 접근 가능
    // @GetMapping("/api/v1/admin")
    // public String admin() {
    //     return "admin";
    // }

}
