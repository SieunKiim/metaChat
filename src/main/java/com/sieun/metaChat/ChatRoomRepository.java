package com.sieun.metaChat;

import com.sieun.metaChat.dto.ChatRoomDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    @PostConstruct
    private void init() {
        chatRoomDTOMap = new LinkedHashMap<>();
        createChatRoomDTO("공학관", 37.337566, 127.267790);
        createChatRoomDTO("교양관", 37.339796, 127.272037);
        createChatRoomDTO("기숙사", 37.595411, 127.059305); // 기숙사 위도 좌표 : 37.334856, 127.263103
        createChatRoomDTO("도서관", 37.336612, 127.268472);
        createChatRoomDTO("백년관", 37.337301, 127.265805);
        createChatRoomDTO("어문관", 37.338213, 127.273026);
        createChatRoomDTO("인경관", 37.339729, 127.274556);
        createChatRoomDTO("후생관", 37.337753, 127.268633);
    }

    public List<ChatRoomDTO> findAllRooms() {
        //채팅방을 순서대로
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);
        return result;
    }

    public ChatRoomDTO findRoomById(String id) {
        return chatRoomDTOMap.get(id);
    }

    public ChatRoomDTO createChatRoomDTO(String name, double roomLatitude, double roomLongitude) {
        ChatRoomDTO room = ChatRoomDTO.create(name, roomLatitude, roomLongitude);
        chatRoomDTOMap.put(room.getRoomId(), room);
        return room;
    }
}
