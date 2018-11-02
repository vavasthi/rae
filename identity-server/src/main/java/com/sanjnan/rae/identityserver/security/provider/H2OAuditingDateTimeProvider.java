package com.sanjnan.rae.identityserver.security.provider;

import com.sanjnan.rae.identityserver.service.DateTimeService;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * Created by vinay on 1/28/16.
 */
public class H2OAuditingDateTimeProvider implements DateTimeProvider {

    private final DateTimeService dateTimeService;

    public H2OAuditingDateTimeProvider(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public Optional<TemporalAccessor> getNow() {

        return Optional.of(dateTimeService.getCurrentDateAndTime());
    }
}