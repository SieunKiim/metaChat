package com.sieun.metaChat.controller;

import com.sieun.metaChat.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/kakao")
public class LoginController {

    private final LoginService loginService;

    @GetMapping()
    public void kakaoCodeCallback(@RequestParam String code) throws IOException {
        String token = loginService.getToken(code);
        Map<String, Object> userInfo = loginService.getUserInfo(token);
        System.out.println(userInfo);
    }

    @GetMapping("/token")
    public void kakaoTokenCallback() {
        System.out.println("xhzmd");
    }
}
