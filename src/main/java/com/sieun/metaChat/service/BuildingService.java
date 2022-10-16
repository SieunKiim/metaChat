package com.sieun.metaChat.service;

import com.sieun.metaChat.dto.response.ResponseBuildingDto;
import com.sieun.metaChat.entity.Building;
import com.sieun.metaChat.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.scan.UrlJar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public List<Building> getBuildingList() {
        return buildingRepository.findAll();
    }

    public ResponseBuildingDto enterBuilding(UUID buildingId) {
        Building building = buildingRepository.findById(buildingId).orElse(null);
        return new ResponseBuildingDto(building.getId(), building.getBuildingName());
    }
}
