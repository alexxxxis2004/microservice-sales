package com.example.agency.cars.controller;

import com.example.agency.cars.dto.PaymentMethodRequest;
import com.example.agency.cars.dto.PaymentMethodResponse;
import com.example.agency.cars.service.PaymentMethodService;
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
@RequestMapping("/api/v1/paymentmethods")
@CrossOrigin(origins = "*")
@Tag(name = "Payment Methods", description = "Payment method management")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @Operation(summary = "Register a new payment method")
    @ApiResponse(responseCode = "201", description = "Payment method successfully registered",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PaymentMethodResponse.class)))
    @PostMapping
    public ResponseEntity<PaymentMethodResponse> registerPaymentMethod(
            @Valid @RequestBody PaymentMethodRequest data) {
        PaymentMethodResponse saved = service.registerPaymentMethod(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "List all payment methods")
    @ApiResponse(responseCode = "200", description = "List of payment methods successfully obtained",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PaymentMethodResponse.class))))
    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> listPaymentMethods() {
        return ResponseEntity.ok(service.listPaymentMethods());
    }
}
