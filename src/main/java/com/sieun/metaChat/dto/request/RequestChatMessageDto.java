package com.sieun.metaChat.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChatMessageDto {
    private String roomId;
    private String writer;
    private double chatLatitude;
    private double chatLongitude;
}
