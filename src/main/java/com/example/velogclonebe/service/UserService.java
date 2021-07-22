package com.example.velogclonebe.service;


import com.example.velogclonebe.domain.dto.response.UserInfoResponseDto;
import com.example.velogclonebe.domain.dto.response.UserMypageResponseDto;
import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.exception.ApiRequestException;
import com.example.velogclonebe.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String BASIC_PROFILE = "https://velogclonecoding.s3.ap-northeast-2.amazonaws.com/profile/basic_profile.png";
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public void setUser(User user) {
        String username = user.getUsername();

        if (userRepository.existsByUsername(username)) {
            throw new ApiRequestException("이미 존재하는 유저이름입니다.");
        }
        user.setProfileUrl(BASIC_PROFILE);
        userRepository.save(user);
    }


    // 토큰으로 유저정보 가져오기
    @Transactional
    public UserInfoResponseDto getUserInfo(String username) {

        User user = userRepository.findByUsername(username);
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(user);
        return userInfoResponseDto;
    }

    @Transactional
    public UserMypageResponseDto getMypage(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        return new UserMypageResponseDto(username, user.getProfileUrl());
    }

    @Transactional
    public Map<String, String> modifyProfileImage(MultipartFile file, String username) throws IOException {

        User user = userRepository.findByUsername(username);
        if (user.getProfileUrl() != BASIC_PROFILE) {
            s3Uploader.deletes3(user.getProfileUrl());
        }

        String profileUrl = s3Uploader.upload(file, "profile");
        user.setProfileUrl(profileUrl);

        // System.out.println("username :::::::::: " + user.getUsername());
        // System.out.println("profileUrl :::::::::: " + user.getProfileUrl());

        Map<String, String> updatedUserInfo = new HashMap();
        updatedUserInfo.put("username", user.getUsername());
        updatedUserInfo.put("profileUrl", user.getProfileUrl());

        return updatedUserInfo;

    }

}
