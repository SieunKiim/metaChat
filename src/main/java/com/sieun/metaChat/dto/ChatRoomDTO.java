package com.sieun.metaChat.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private double roomLatitude;
    private double roomLongitude;
//    private Set<WebSocketSession> session = new HashSet<>();

    public static ChatRoomDTO create(String name, double roomLatitude, double roomLongitude) {
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        room.roomLatitude = roomLatitude;
        room.roomLongitude = roomLongitude;
        return room;
    }
}
