package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="us holiday info from https://date.nager.at/api/v2/publicholidays/2023/us")
@Slf4j
@RequestMapping("/api/publicholiday")

@RestController
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary="Get public holiday info",description= "Returns public holiday info via https://date.nager.at/api/v2/publicholidays/2023/us")
    @GetMapping("/get")

    public ResponseEntity<String> getHoliday(
        @Parameter(name="year", description="holiday year", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", description="abbreviation of country", example="US") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getHoliday: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}
