package com.CK.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(5100, "Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST (4100,"Parametre hatasi", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS(4110,"Böyle bir kullanici adi sistemde mevcut...", HttpStatus.BAD_REQUEST),
    PASSWORDS_DONT_MATCH(4111,"Sifreler ayni olmalidir...",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4112,"Kullanici adi veya sifre hatalidir...",HttpStatus.BAD_REQUEST),


    USER_NOT_FOUND(4113,"Böyle bir kullanici bulunamadi...",HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_ERROR(4114,"Aktivasyon kod hatasi..." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4115,"Geçersiz token" ,HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4116,"Token olusturulamadi..." , HttpStatus.BAD_REQUEST ),
    ACCOUNT_NOT_ACTIVE(4117,"Hesabiniz aktif degildir..." , HttpStatus.FORBIDDEN ),
    ROLE_NOT_FOUND(4118,"ROL BULUNAMADI" ,HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4119,"Kullanici profili olusturulamadi...",HttpStatus.BAD_REQUEST),
    AUTHORIZATION_DENIED(4120,"Yetkiniz yok...",HttpStatus.UNAUTHORIZED),
    COMMENT_NOT_FOUND(4121,"Yorum bulunamadi...",HttpStatus.BAD_REQUEST);



    private int code;
    private String message;
    private HttpStatus httpStatus;
}
