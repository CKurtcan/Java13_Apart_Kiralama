package com.CK.repository;

import com.CK.entity.HotelProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPropertiesRepository extends MongoRepository<HotelProperties, String> {
}
