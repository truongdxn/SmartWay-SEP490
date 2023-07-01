package com.resteam.smartway.service;

import com.resteam.smartway.domain.MenuItemCategory;
import com.resteam.smartway.domain.Restaurant;
import com.resteam.smartway.domain.Zone;
import com.resteam.smartway.repository.ZoneRepository;
import com.resteam.smartway.security.SecurityUtils;
import com.resteam.smartway.service.dto.ZoneDTO;
import com.resteam.smartway.service.mapper.ZoneMapper;
import com.resteam.smartway.web.rest.errors.BadRequestAlertException;
import com.resteam.smartway.web.rest.errors.RestaurantInfoNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    private static final String ENTITY_NAME = "menuItemCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Override
    public List<ZoneDTO> loadAllZones() {
        String restaurantId = SecurityUtils.getCurrentRestaurantId().orElseThrow(RestaurantInfoNotFoundException::new);

        List<Zone> zoneList = zoneRepository.findAllByRestaurantOrderByCreatedDateDesc(new Restaurant(restaurantId));
        return zoneMapper.toDto(zoneList);
    }

    @Override
    @SneakyThrows
    public ZoneDTO createZone(ZoneDTO zoneDTO) {
        String restaurantId = SecurityUtils.getCurrentRestaurantId().orElseThrow(RestaurantInfoNotFoundException::new);

        zoneRepository
            .findOneByRestaurantAndName(new Restaurant(restaurantId), zoneDTO.getName())
            .ifPresent(m -> {
                throw new BadRequestAlertException(applicationName, ENTITY_NAME, "existed");
            });

        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone.setRestaurant(new Restaurant(restaurantId));

        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    @Override
    public ZoneDTO updateZone(ZoneDTO zoneDTO) {
        String restaurantId = SecurityUtils.getCurrentRestaurantId().orElseThrow(RestaurantInfoNotFoundException::new);

        Zone zone = zoneRepository
            .findByRestaurantAndId(new Restaurant(restaurantId), zoneDTO.getId())
            .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));

        zone.setName(zoneDTO.getName());
        Zone resultZone = zoneRepository.save(zone);

        return zoneMapper.toDto(resultZone);
    }

    @Override
    public void deleteZone(UUID id) {
        String restaurantId = SecurityUtils.getCurrentRestaurantId().orElseThrow(RestaurantInfoNotFoundException::new);

        Optional<Zone> zone = zoneRepository.findByRestaurantAndId(new Restaurant(restaurantId), id);
        if (zone.isEmpty()) {
            throw new BadRequestAlertException("Menu item category not found", ENTITY_NAME, "entityNotFound");
        }
        zoneRepository.delete(zone.get());
    }
}
