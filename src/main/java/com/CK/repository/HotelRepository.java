package com.CK.repository;

import com.CK.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    String findNameById(String hotelId);

    Hotel findIdByName(String hotelName);

    List<Hotel> findByLocation(String location);
}
