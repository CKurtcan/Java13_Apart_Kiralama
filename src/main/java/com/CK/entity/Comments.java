package com.CK.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "comments")
public class Comments extends BaseEntity {

    @Id
    private String id;

    private String userId;
    private String hotelId;
    private Double rating;
    private String date;
    private String comment;
}
