package com.example.velogclonebe.service;


import com.example.velogclonebe.domain.dto.response.UserInfoResponseDto;
import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void setUser(User user) {
        String username = user.getUsername();

        if (userRepository.existsByUsername(username)) {
            throw new ApiRequestException("이미 존재하는 유저이름입니다.");
        }
        userRepository.save(user);
    }


    // 토큰으로 유저정보 가져오기
    @Transactional
    public UserInfoResponseDto getUserInfo(String username) {

        User user = userRepository.findByUsername(username);
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(user);
        return userInfoResponseDto;
    }
}
