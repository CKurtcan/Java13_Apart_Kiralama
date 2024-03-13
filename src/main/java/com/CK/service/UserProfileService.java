package com.CK.service;

import com.CK.dto.request.CreateProfileRequestDto;
import com.CK.dto.request.RegisterRequestDto;
import com.CK.dto.request.UpdateRequestDto;
import com.CK.entity.UserProfile;
import com.CK.exception.ErrorType;
import com.CK.exception.HotelReservationManagerException;
import com.CK.mapper.ReservationMapper;
import com.CK.repository.UserProfileRepository;
import com.CK.utility.other.JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ReservationMapper reservationMapper;
    private final JWTManager jwtManager;

    public void createProfile(CreateProfileRequestDto dto) {
        UserProfile userProfile = reservationMapper.INSTANCE.fromCreateProfileRequestDtoToUserProfile(dto);
        userProfileRepository.save(userProfile);
    }

    public UserProfile updateProfile(UpdateRequestDto dto) {
        Optional<String> optionalId = jwtManager.getIdFromToken(dto.getToken());
        if (optionalId.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(optionalId.get());
        if (optionalUserProfile.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.USER_NOT_FOUND);
        }
        UserProfile userProfile = optionalUserProfile.get();
        userProfile.setIdNum(dto.getIdNum());
        userProfile.setName(dto.getName());
        userProfile.setSurname(dto.getSurname());
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    public UserProfile getUserProfile(String token) {
        Optional<String> optionalId = jwtManager.getIdFromToken(token);
        if (optionalId.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(optionalId.get());
        if (optionalUserProfile.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.USER_NOT_FOUND);
        }
        return optionalUserProfile.get();
    }
}
