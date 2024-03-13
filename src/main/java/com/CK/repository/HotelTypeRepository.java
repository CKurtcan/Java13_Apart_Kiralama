package com.CK.repository;

import com.CK.entity.HotelType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelTypeRepository extends MongoRepository<HotelType, String> {
}
