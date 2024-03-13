package com.CK.repository;

import com.CK.entity.UserProfile;
import com.CK.utility.enums.ERoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    ERoles findRoleById(String id);

    String findUsernameById(String id);
}
