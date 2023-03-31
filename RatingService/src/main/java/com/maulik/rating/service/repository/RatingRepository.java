package com.maulik.rating.service.repository;

import com.maulik.rating.service.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    //Customer finder methods
    List<Rating> findAllByUserId(String userId);

    List<Rating> findAllByHotelId(String hotelId);
}
