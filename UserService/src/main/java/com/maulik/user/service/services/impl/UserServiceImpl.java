package com.maulik.user.service.services.impl;

import com.maulik.user.service.entities.Hotel;
import com.maulik.user.service.entities.Rating;
import com.maulik.user.service.entities.User;
import com.maulik.user.service.exceptions.ResourceNotFoundException;
import com.maulik.user.service.repositories.UserRepository;
import com.maulik.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of the userRepository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server !!" + userId));

        //fetch rating of the above user from ratingService
        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8097/ratings/users/" +user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);

        //convert array into array list
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //API call to hotel service to get hotel --- http://localhost:8097/ratings/hotels/eb7b00ec-a8e0-4554-8e1e-9a3d8062dfce

            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8097/ratings/hotels/eb7b00ec-a8e0-4554-8e1e-9a3d8062dfce" +rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            // set hotel to get rating
            rating.setHotel(hotel);

            //return the hotel rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
