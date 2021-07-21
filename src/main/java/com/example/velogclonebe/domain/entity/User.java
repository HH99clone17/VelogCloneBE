package com.example.velogclonebe.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String roles; // 한 유저의 롤이 하나 이상일 때의 세팅 (USER, ADMIN <- 이런 형식으로 저장)


    @Column(nullable = true)
    private String profileUrl;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

}
