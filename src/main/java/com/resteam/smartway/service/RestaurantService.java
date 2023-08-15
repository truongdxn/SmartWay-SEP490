package com.resteam.smartway.service;

import com.resteam.smartway.service.dto.RestaurantDTO;
import java.util.List;

public interface RestaurantService {
    RestaurantDTO getRestaurantInfo();

    public List<RestaurantDTO> getAllRestaurantDTOs();
}
