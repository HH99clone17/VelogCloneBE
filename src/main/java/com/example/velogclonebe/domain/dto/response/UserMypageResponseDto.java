package com.example.velogclonebe.domain.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMypageResponseDto {
    private String username;
    private String profileUrl;

    public UserMypageResponseDto(String username, String profileUrl){
        this.username = username;
        this.profileUrl = profileUrl;
    }
}