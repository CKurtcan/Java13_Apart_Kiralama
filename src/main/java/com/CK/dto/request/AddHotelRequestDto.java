package com.CK.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequestDto {

    private String token;
    private String name;
    private String location;
    private String point;
    private String images;
    private String facilities;
}
