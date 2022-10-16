package com.sieun.metaChat.controller;


import com.sieun.metaChat.dto.response.ResponseBuildingDto;
import com.sieun.metaChat.entity.Building;
import com.sieun.metaChat.service.BuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/university/buildings")
public class RoomController {
    private final BuildingService buildingService;

    @GetMapping
    public ModelAndView getBuildings() {
        List<Building> buildingList = buildingService.getBuildingList();
        List<ResponseBuildingDto> responseBuildingDtoList = new ArrayList<>();
        for (Building building : buildingList) {
            responseBuildingDtoList.add(new ResponseBuildingDto(building.getId(), building.getBuildingName()));
        }
        ModelAndView mv = new ModelAndView("roomList");
        mv.addObject("list", responseBuildingDtoList);
        return mv;
    }


    @GetMapping( "/{buildingId}")
    public ModelAndView enterBuilding(@PathVariable UUID buildingId) {
        ResponseBuildingDto responseBuildingDto = buildingService.enterBuilding(buildingId);
        ModelAndView mv = new ModelAndView("room");
        mv.addObject("buildingId", responseBuildingDto);
        return mv;
    }
}
