package com.maulik.hotel.service.services;

import com.maulik.hotel.service.entites.Hotel;

import java.util.List;

public interface HotelService {

    //Create
    Hotel create(Hotel hotel);

    //Get all
    List<Hotel> getAll();

    //Get single
    Hotel get(String id);
}
