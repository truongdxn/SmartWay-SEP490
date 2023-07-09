package com.resteam.smartway.service;

import com.resteam.smartway.domain.DiningTable;
import com.resteam.smartway.domain.Restaurant;
import com.resteam.smartway.repository.DiningTableRepository;
import com.resteam.smartway.security.SecurityUtils;
import com.resteam.smartway.service.dto.DiningTableDTO;
import com.resteam.smartway.service.mapper.DiningTableMapper;
import com.resteam.smartway.web.rest.errors.BadRequestAlertException;
import com.resteam.smartway.web.rest.errors.RestaurantInfoNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class DiningTableServiceImpl implements DiningTableService {

    private static final String ENTITY_NAME = "dining_table";

    private final DiningTableRepository diningTableRepository;

    private final DiningTableMapper diningTableMapper;

    @Override
    public Page<DiningTableDTO> loadDiningTablesWithSearch(Pageable pageable, String searchText, List<String> zoneIds) {
        if (searchText != null) searchText = searchText.toLowerCase();
        List<UUID> zoneUuidList = null;
        if (zoneIds != null && zoneIds.size() > 0) zoneUuidList =
            zoneIds.stream().map(c -> UUID.fromString(c)).collect(Collectors.toList());
        Page<DiningTable> diningTablePage = diningTableRepository.findWithFilterParams(searchText, zoneUuidList, pageable);

        return diningTablePage.map(item -> {
            DiningTableDTO diningTable = diningTableMapper.toDto(item);
            return diningTable;
        });
    }

    @Override
    @SneakyThrows
    public DiningTableDTO createDiningTable(DiningTableDTO diningTableDTO) {
        DiningTable diningTable = diningTableMapper.toEntity(diningTableDTO);
        diningTable.setIsFree(true);
        diningTable.setIsActive(true);

        return diningTableMapper.toDto(diningTableRepository.save(diningTable));
    }

    @Override
    @SneakyThrows
    public DiningTableDTO updateDiningTable(DiningTableDTO diningTableDTO) {
        DiningTable diningTable = diningTableRepository
            .findById(diningTableDTO.getId())
            .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));

        diningTableMapper.partialUpdate(diningTable, diningTableDTO);

        DiningTable result = diningTableRepository.save(diningTable);
        return diningTableMapper.toDto(result);
    }
}
