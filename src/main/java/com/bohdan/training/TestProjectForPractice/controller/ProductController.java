package com.bohdan.training.TestProjectForPractice.controller;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ErrorResponseDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import com.bohdan.training.TestProjectForPractice.service.ProductService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
@Tag(name = "Продукти", description = "Керування товарами")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Отримати товари сторінками", description = "Повертає список усіх продуктів")
    @GetMapping()
    public ResponseEntity<Page<ProductDto>> getAll(//@PageableDefault(sort = "name", direction = Sort.Direction.ASC)
                                                       Pageable pageable) {
        return ResponseEntity.ok(productService.getAll(pageable));
    }

    @Operation(summary = "Отримати товар по ID")
    @Parameter(name = "id", description = "ID товара", example = "1")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found"
            ),
            @ApiResponse(
                    responseCode = "404docker compose up -d",
                    description = "Product not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(
            @PathVariable
            @Positive(message = "ID должен быть положительным")
            @NotNull(message = "ID не может быть пустым")
            Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "Створити новий товар",  description = "Виклик на створення нового товару")
    @ApiResponse(responseCode = "201", description = "Товар успішно створенний")
    @PostMapping()
    public ResponseEntity<IdDto> create(@RequestBody @Valid ProductUpsertDto dto) {
        IdDto saved = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Оновити товар")
    @ApiResponse(description = "Товар успішно Оновлений")
    @PutMapping("/{id}")
    public void update(
            @PathVariable
            @Positive(message = "ID должен быть положительным")
            @NotNull(message = "ID не может быть пустым")
            Long id,
            @RequestBody @Valid ProductUpsertDto productDtoResponse) {
        productService.update(id, productDtoResponse);
    }

    @Operation(summary = "Видалити товар")
    @ApiResponse(description = "Товар успішно видалено")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable
            @Positive(message = "ID должен быть положительным")
            @NotNull(message = "ID не может быть пустым")
            Long id) {
        productService.delete(id);
    }
}
