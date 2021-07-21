package com.example.velogclonebe.domain.dto.response;
import com.example.velogclonebe.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private Long userId;
    private String username;
//    private String userThumbnail;

    public UserInfoResponseDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        // 추후 썸네일 추가
        // this.userThumbnail = user.getUserThumbnail();
    }
}
