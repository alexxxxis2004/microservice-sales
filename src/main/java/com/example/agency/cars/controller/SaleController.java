package com.example.agency.cars.controller;

import com.example.agency.cars.dto.SaleRequest;
import com.example.agency.cars.dto.SaleResponse;
import com.example.agency.cars.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "*")
@Tag(name = "Sales", description = "Manage customer sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @Operation(summary = "Register a new sale")
    @ApiResponse(responseCode = "201", description = "Sale successfully registered",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponse.class)))
    @PostMapping
    public ResponseEntity<SaleResponse> registerSale(@Valid @RequestBody SaleRequest data) {
        SaleResponse saved = service.registerSale(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Get sale by ID")
    @ApiResponse(responseCode = "200", description = "Sale found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponse.class)))
    @GetMapping("/{idSale}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Integer idSale) {
        SaleResponse response = service.getSaleById(idSale);
        return ResponseEntity.ok(response);
    }
}
