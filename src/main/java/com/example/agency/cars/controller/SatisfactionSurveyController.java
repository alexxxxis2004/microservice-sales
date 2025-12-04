package com.example.agency.cars.controller;

import com.example.agency.cars.dto.SatisfactionSurveyRequest;
import com.example.agency.cars.dto.SatisfactionSurveyResponse;
import com.example.agency.cars.service.SatisfactionSurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
@CrossOrigin(origins = "*")
@Tag(name = "Satisfaction Surveys", description = "Manage customer satisfaction surveys")
public class SatisfactionSurveyController {

    private final SatisfactionSurveyService service;

    public SatisfactionSurveyController(SatisfactionSurveyService service) {
        this.service = service;
    }

    @Operation(summary = "Register a new satisfaction survey")
    @ApiResponse(responseCode = "201", description = "Survey successfully registered",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SatisfactionSurveyResponse.class)))
    @PostMapping
    public ResponseEntity<SatisfactionSurveyResponse> registerSurvey(@Valid @RequestBody SatisfactionSurveyRequest data) {
        SatisfactionSurveyResponse saved = service.registerSurvey(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Get surveys by customer ID")
    @ApiResponse(responseCode = "200", description = "Surveys found",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SatisfactionSurveyResponse.class))))
    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<SatisfactionSurveyResponse>> getSurveysByCustomer(@PathVariable Integer idCustomer) {
        List<SatisfactionSurveyResponse> list = service.getSurveysByCustomer(idCustomer);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get overall satisfaction average")
    @ApiResponse(responseCode = "200", description = "Average satisfaction value returned",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/average")
    public ResponseEntity<Double> getOverallAverage() {
        return ResponseEntity.ok(service.getOverallSatisfactionAverage());
    }
}
