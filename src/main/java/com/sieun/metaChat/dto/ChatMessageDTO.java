package com.sieun.metaChat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    private String roomId;
    private String writer;
    private String message;
    private double chatLatitude;
    private double chatLongitude;
}
