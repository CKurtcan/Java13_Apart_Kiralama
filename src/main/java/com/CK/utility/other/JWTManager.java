package com.CK.utility.other;

import com.CK.dto.response.CheckRoleResponseDto;
import com.CK.exception.ErrorType;
import com.CK.exception.HotelReservationManagerException;
import com.CK.mapper.ReservationMapper;
import com.CK.utility.enums.ERoles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JWTManager {

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.audience}")
    private String audience;

    private final ReservationMapper reservationMapper;

    public Optional<String> createToken(String id, ERoles role) {
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
            }
            String id = String.valueOf(decodedJWT.getClaim("id"));
            return Optional.of(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
            }
            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public CheckRoleResponseDto getRoleAndIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
            }
            String id = String.valueOf(decodedJWT.getClaim("id"));
            String role = decodedJWT.getClaim("role").asString();
            return reservationMapper.INSTANCE.fromIdAndRoleToCheckRoleResponseDto(id, role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
        }
    }

}
