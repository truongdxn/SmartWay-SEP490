package com.resteam.smartway.repository;

import com.resteam.smartway.domain.MenuItemCategory;
import com.resteam.smartway.domain.Restaurant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemCategoryRepository extends JpaRepository<MenuItemCategory, UUID> {
    List<MenuItemCategory> findAllByRestaurantOrderByCreatedDateDesc(Restaurant restaurant);
    Optional<MenuItemCategory> findOneByRestaurantAndName(Restaurant restaurant, String name);

    Optional<MenuItemCategory> findByRestaurantAndId(Restaurant restaurant, UUID uuid);

    Boolean existsByIdAndRestaurant(UUID id, Restaurant restaurant);
}
