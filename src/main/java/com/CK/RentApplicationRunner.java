package com.CK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RentApplicationRunner.class, args);
        /**
         * Tatile çıkacaklar için kiraalama uygulaması yazılacak.
         * 1- Mevcut bir örnek incelenecek ve gerekli bilgiler toplanacak madde madde yazılacak.
         * (https://www.etstur.com/)
         * 2- Security (JWT) // 2
         * 3- Sql Seması // tamam
         * 4- Mongo DB // tamam
         * 5- Redis // 3
         * 6- Login-Register(UserProfile) // tamam
         */
    }
}