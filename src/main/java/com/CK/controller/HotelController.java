package com.CK.controller;

import com.CK.dto.request.AddHotelRequestDto;
import com.CK.dto.response.HotelResponseDto;
import com.CK.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.CK.constant.RestApiUrls.*;

@RestController
@RequestMapping(HOTEL)
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(ADD_HOTEL)
    public ResponseEntity<Boolean> addHotel(@RequestBody AddHotelRequestDto dto) {
        return ResponseEntity.ok(hotelService.addHotel(dto));
    }

    @GetMapping(ALL_HOTELS)
    public ResponseEntity<List<HotelResponseDto>> findAll() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping(FIND_BY_LOCATION)
    public ResponseEntity<List<HotelResponseDto>> findByLocation(String location) {
        return ResponseEntity.ok(hotelService.findByLocation(location));
    }

    /**
     * fınd by location
     * find by type
     * find by name
     */
}
