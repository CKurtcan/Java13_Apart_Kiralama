package com.CK.service;

import com.CK.dto.request.AddHotelRequestDto;
import com.CK.dto.response.CheckRoleResponseDto;
import com.CK.dto.response.HotelResponseDto;
import com.CK.entity.*;
import com.CK.exception.ErrorType;
import com.CK.exception.HotelReservationManagerException;
import com.CK.mapper.ReservationMapper;
import com.CK.repository.*;
import com.CK.utility.enums.ERoles;
import com.CK.utility.other.JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ReservationMapper reservationMapper;
    private final JWTManager jwtManager;
    private final UserProfileRepository userProfileRepository;
    private final CommentsRepository commentsRepository;
    private final MainPropertiesRepository mainPropertiesRepository;
    private final HotelPropertiesRepository HotelPropertiesRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Boolean addHotel(AddHotelRequestDto dto) {
        CheckRoleResponseDto checkDto = jwtManager.getRoleAndIdFromToken(dto.getToken());
        String roles = String.valueOf(userProfileRepository.findRoleById(checkDto.getId()));
        if (!roles.equals(checkDto.getRole()) || roles.equals(ERoles.ROLE_USER.toString())) {
            throw new HotelReservationManagerException(ErrorType.AUTHORIZATION_DENIED);
        }
        // FACILITIES kısmının düzenlenmesi lazım
        Hotel hotel = reservationMapper.INSTANCE.fromAddHotelRequestDtoToHotel(dto);
        return true;
    }

    public Boolean updateRating(Double rating, String hotelId) {
        double sum = 0;
        List<Comments> comments = commentsRepository.findAllByHotelId(hotelId);
        int size = comments.size();
        if (size == 0) {
            return false;
        }
        for(Comments comment : comments) {
            double rate = comment.getRating();
            sum += rate;
        }
        Double average = sum/ size;
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        hotel.get().setPoint(average);
        return true;
    }

    public List<HotelResponseDto> findAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponseDto> dtoList = new ArrayList<>();
        hotels.forEach(hotel -> dtoList.add(reservationMapper.INSTANCE.fromHotelToHotelResponseDto(hotel)));
        dtoList.stream().sorted((a, b) -> b.getPoint().compareTo(a.getPoint()));
        return dtoList;
    }

    public List<HotelResponseDto> findByLocation(String location) {
        List<Hotel> hotels = hotelRepository.findByLocation(location);
        List<HotelResponseDto> dtoList = new ArrayList<>();
        hotels.forEach(hotel -> dtoList.add(reservationMapper.INSTANCE.fromHotelToHotelResponseDto(hotel)));
        return dtoList;
    }


//         *  +----- otel/filter-list (sol tarafta bulunan filtreleme başlıklarını listelemek için kullanılacak)
//         *  +----- otel/find-search (aram çubuğuna yazılan ifadeye göre filtreleme yaparak otel listesi dönecek)
}
