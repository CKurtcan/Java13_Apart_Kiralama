package com.CK.service;

import com.CK.repository.MainPropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPropertiesService {

    private final MainPropertiesRepository hotelPropertiesRepository;
}
