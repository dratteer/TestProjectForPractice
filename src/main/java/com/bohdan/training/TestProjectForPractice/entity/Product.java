package com.bohdan.training.TestProjectForPractice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;

@Entity
@Table(schema = "jdbo",name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //refactoring

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 512)
    private String description;

    @Column(nullable = false)
    private Integer stockQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_status_id", nullable = false)
    private ProductStatus productStatus;

    @Column()
    private Long supplierProductId;
}

