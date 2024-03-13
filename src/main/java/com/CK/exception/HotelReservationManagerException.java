package com.CK.exception;

import lombok.Getter;



@Getter
public class HotelReservationManagerException extends RuntimeException{

    private final ErrorType errorType;

    public HotelReservationManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public HotelReservationManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}