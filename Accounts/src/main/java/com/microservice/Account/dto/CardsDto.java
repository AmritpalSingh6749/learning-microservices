package com.microservice.Account.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {

    @NotEmpty(message = "mobileNumber cant be empty or null")
    @Pattern(regexp = "$|[0-9]{10}")
    private String mobileNumber;

    @NotEmpty(message = "cardType cant be empty or mull")
    private String cardType;

    @Pattern(regexp = "$|[0-9]{12}")
    private String cardNumber;

    @Positive(message = "totalLimit should be greater than zero")
    @NotNull(message = "totalAmount cannot be null")
    private Integer totalLimit;

    @PositiveOrZero(message = "amountUsed should be equal or greater than zero")
    private Integer amountUsed = 0;

    @Nullable
    private Integer remainingAmount;
}
