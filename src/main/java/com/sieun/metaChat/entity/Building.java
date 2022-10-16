package com.sieun.metaChat.entity;

import com.sieun.metaChat.repository.BuildingRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "building_latitude", nullable = false)
    private double buildingLatitude;

    @Column(name = "building_longitude", nullable = false)
    private double buildingLongitude;

    public Building(String buildingName, double buildingLatitude, double buildingLongitude) {
        this.buildingName = buildingName;
        this.buildingLatitude = buildingLatitude;
        this.buildingLongitude = buildingLongitude;
    }
}