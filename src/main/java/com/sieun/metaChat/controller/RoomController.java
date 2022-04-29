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

    @GetMapping(value = "/rooms")
    public ModelAndView rooms() {
        log.info("# ALL Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", repository.findAllRooms());

        return mv;
    }

    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr) {
        log.info("#Create chat Room, name : " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        log.info("# Get Chat Room, roomID : " + roomId);
        model.addAttribute("room", repository.findRoomById(roomId));
    }
}
