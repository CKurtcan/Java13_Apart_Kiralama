package com.CK.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private String username;
    private String hotelName; // name olabilir
    private Double rating;
    private Date date;
    private String comment;
}
