package com.CK.entity;

import com.CK.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rooms")
public class Room extends BaseEntity {

    @Id
    private String id;

    private String name;
    private String image;
    private String properties;
    private EStatus status;
    private String price;

    private String hotelId;
}
