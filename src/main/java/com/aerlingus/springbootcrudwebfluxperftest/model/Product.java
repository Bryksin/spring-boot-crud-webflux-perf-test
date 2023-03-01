package com.aerlingus.springbootcrudwebfluxperftest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    Long productId;

    @NotNull
    @Size(min = 3, max = 100)
    String productName;

    @Size(min = 0, max = 1000)
    String productDescription;

    @NotNull
    float productPrice;

    int productStock;
}
