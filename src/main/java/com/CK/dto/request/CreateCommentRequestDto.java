package com.CK.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequestDto {

    private String token;
    private String hotelId;
    private Double rating;
    private String date;
    private String comment;
}
