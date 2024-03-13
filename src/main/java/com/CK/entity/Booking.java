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
@Document
public class Booking {

    @Id
    private String id;

    private String roomId;
    private String userId;
    private EStatus status;
    private String startDate;
    private String endDate;
    private String totalPrice;
    private String totalParticipant;
}
