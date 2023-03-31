package com.maulik.rating.service.services;

import com.maulik.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getAllRatings();

    //get all rating by userId
    List<Rating> getRatingsByUserId(String userId);

    //get all by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
