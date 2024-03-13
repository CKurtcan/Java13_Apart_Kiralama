package com.CK.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDto {

    private String name;
    private String location;
    private Double point;
    private String images;
    private String facilities;
}
