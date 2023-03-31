package com.maulik.hotel.service.repositories;

import com.maulik.hotel.service.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
