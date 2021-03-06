package com.sieun.metaChat.controller;


import com.sieun.metaChat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Slf4j
public class RoomController {

    private final ChatRoomRepository repository;
    //채팅방 목록 조회

    @GetMapping( "/rooms")
    public ModelAndView rooms() {
        long startTime = System.currentTimeMillis();

        log.info("# ALL Chat Rooms");
        ModelAndView mv = new ModelAndView("roomList");

        mv.addObject("list", repository.findAllRooms());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        log.info(String.valueOf(elapsedTime));
        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr) {
        log.info("#Create chat Room, name : " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name, 0, 0)); // 위도 경도 일당 0,0으로
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(String roomId, Model model) { //@RequestParam 생략 가능
        log.info("# Get Chat Room, roomID : " + roomId);
        model.addAttribute("room", repository.findRoomById(roomId));
        return "room";
    }
}
