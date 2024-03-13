package com.CK.repository;

import com.CK.entity.MainProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainPropertiesRepository extends MongoRepository<MainProperties, String> {

}
