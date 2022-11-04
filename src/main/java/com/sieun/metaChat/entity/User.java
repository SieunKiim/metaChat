package com.sieun.metaChat.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String email;

    @Column(nullable = false)
    private String nickname;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}