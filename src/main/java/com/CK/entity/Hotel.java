package com.CK.entity;

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
@Document
public class Hotel extends BaseEntity{

    @Id
    private String id;

    private String name;
    private String location;
    private Double point;
    private String images;
    private String facilities;
}
