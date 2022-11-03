package com.sieun.metaChat.controller;

import com.sieun.metaChat.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println("코드는 : "+code);

    }

}
