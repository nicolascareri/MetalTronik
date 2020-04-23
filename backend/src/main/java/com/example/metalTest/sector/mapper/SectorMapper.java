package com.example.metalTest.sector.mapper;

import com.example.metalTest.sector.controller.request.SectorRequest;
import com.example.metalTest.sector.domain.Sector;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectorMapper {
    Sector sectorRequestToSector(SectorRequest sectorRequest);
}
