package com.sieun.metaChat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WebChatController {
    @GetMapping("/")
    public String chatGet() {
        log.info("@WebChatController, chat GET()");

        return "chat";
    }
}
