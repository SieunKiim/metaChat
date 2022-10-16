package com.sieun.metaChat.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChatMessageDTO {
    private String roomId;
    private String writer;
    private double chatLatitude;
    private double chatLongitude;
}
