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
        createChatRoomDTO("공학관");
        createChatRoomDTO("교양관");
        createChatRoomDTO("기숙사");
        createChatRoomDTO("도서관");
        createChatRoomDTO("백년관");
        createChatRoomDTO("어문관");
        createChatRoomDTO("인경관");
        createChatRoomDTO("후생관");
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

    public ChatRoomDTO createChatRoomDTO(String name) {
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
}
