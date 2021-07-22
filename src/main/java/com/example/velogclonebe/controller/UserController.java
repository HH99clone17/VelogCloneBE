package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.dto.response.UserInfoResponseDto;
import com.example.velogclonebe.domain.dto.response.UserMypageResponseDto;
import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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
        userService.setUser(user);
        return "회원가입 완료";
    }

    @GetMapping("/api/user")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return userService.getUserInfo(username);
    }

    // TEST - user, manager, admin 권한 접근 가능
    // @GetMapping("/api/v1/user")
    // public String user() {
    //     return "user";
    // }

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

    // 마이페이지 요청
    @GetMapping("/api/user/profile")
    public UserMypageResponseDto getMypage(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getMypage(userDetails);
    }

    // 유저 정보에 이미지 추가
    @PostMapping("/api/user/profile")
    public Map<String, String> modifyProfileImage(@RequestParam(value="file",required = false) MultipartFile file,
                                                  @AuthenticationPrincipal UserDetails userDetails)
            throws IOException {
        String username = userDetails.getUsername();
        return userService.modifyProfileImage(file, username);
    }

}
