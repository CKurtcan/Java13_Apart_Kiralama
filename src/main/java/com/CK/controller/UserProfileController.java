package com.CK.controller;

import com.CK.dto.request.UpdateRequestDto;
import com.CK.entity.Hotel;
import com.CK.entity.UserProfile;
import com.CK.service.HotelService;
import com.CK.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.CK.constant.RestApiUrls.*;

@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final HotelService hotelService;

    @PostMapping(UPDATE)
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile(dto));
    }

    @GetMapping(FIND_ALL_HOTELS)// hotel controllera taşınacak
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    public ResponseEntity<UserProfile> getUserProfile(@RequestParam String token){
        return ResponseEntity.ok(userProfileService.getUserProfile(token));
    }
}
