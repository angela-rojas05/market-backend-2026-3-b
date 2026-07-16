package mx.edu.tecdesoftware.market_backend_2026_3_b.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage purchases in the store")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(summary = "Get all purchases",
            description = "Return a list of all available purchases")
    @ApiResponse(responseCode = "200",
            description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(
                purchaseService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Get purchases by clients",
            description = "Return all purchases of a specific client")
    @ApiResponse(responseCode = "200",
            description = "Purchase(s) found in the category")
    @ApiResponse(responseCode = "404", description = "Purchase(s) not found in the category")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "Client ID",
                    example = "4546221", required = true)
            @PathVariable("id") int clientId) {

        return purchaseService.getByClient(clientId)
                .map(purchases ->
                        new ResponseEntity<>(
                                purchases,
                                HttpStatus.OK))
                .orElse(
                        new ResponseEntity<>(
                                HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Create a new purchase",
            description = "Register a new purchase and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Purchase",
                                    value = """
            { 
                "clientId": "2552243",
                "date": "2026-07-16T17:12:47.176Z",
                "paymentMethod": "E",
                "comment": "Prueba purchase",
                "state": "P"
            }
            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict (duplicate)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(
            @RequestBody Purchase purchase) {

        return new ResponseEntity<>(
                purchaseService.save(purchase),
                HttpStatus.CREATED
        );
    }
}