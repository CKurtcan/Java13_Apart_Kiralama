package com.CK.service;

import com.CK.dto.request.LoginRequestDto;
import com.CK.dto.request.RegisterRequestDto;
import com.CK.entity.Auth;
import com.CK.exception.ErrorType;
import com.CK.exception.HotelReservationManagerException;
import com.CK.mapper.ReservationMapper;
import com.CK.repository.AuthRepository;
import com.CK.utility.other.JWTManager;
import com.CK.utility.other.MailCheckManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final ReservationMapper reservationMapper;
    private final UserProfileService userProfileService;
    private final JWTManager jwtManager;
    private final MailCheckManager mailCheckManager;

    public Boolean register(RegisterRequestDto dto) {
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new HotelReservationManagerException(ErrorType.USER_ALREADY_EXISTS);
        }
        if (!dto.getPassword().equals(dto.getRePassword())) {
            throw new HotelReservationManagerException(ErrorType.PASSWORDS_DONT_MATCH);
        }
        Auth auth = reservationMapper.INSTANCE.fromRegisterRequestDtoToAuth(dto);
        auth.setRole(mailCheckManager.checkMail(dto.getEmail()));
        authRepository.save(auth);
        // aktivasyon kodu eklenmeli
        userProfileService.createProfile(reservationMapper.INSTANCE.fromAuthToCreateProfileDto(auth));
        return true;
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth= authRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.LOGIN_ERROR);
        }
        return jwtManager.createToken(optionalAuth.get().getId(), optionalAuth.get().getRole()).orElseThrow(() ->
        {throw new HotelReservationManagerException(ErrorType.TOKEN_NOT_CREATED);});
    }
}
