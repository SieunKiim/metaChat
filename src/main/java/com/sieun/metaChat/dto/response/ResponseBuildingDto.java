package com.sieun.metaChat.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class ResponseBuildingDto {
    private final UUID buildingId;
    private final String name;
}
