package com.CK.repository;

import com.CK.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends MongoRepository<Comments, String> {
    List<Comments> findAllByUserId(String id);

    List<Comments> findAllByHotelId(String id);
}
