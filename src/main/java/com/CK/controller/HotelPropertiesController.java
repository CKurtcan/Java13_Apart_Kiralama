package com.CK.controller;

import com.CK.service.MainPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HotelPropertiesController {

    private final MainPropertiesService hotelPropertiesService;
}
