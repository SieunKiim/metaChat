package com.sieun.metaChat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class MainController {

    @Value("${spring.security.oauth2.client.registration.kakao.clientId}")	// properties에 선언한 값
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirectUri}")  // properties에 선언한 값
    private String redirectUrl;
    @GetMapping
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("clientId", clientId);
        mv.addObject("redirectUrl", redirectUrl);
        return mv;
    }
}
