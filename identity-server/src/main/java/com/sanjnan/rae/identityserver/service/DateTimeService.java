package com.sanjnan.rae.identityserver.service;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * Created by vinay on 1/28/16.
 */
@Service
public class DateTimeService {

    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}