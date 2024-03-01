package com.microservice.Loans.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class LoansDto {

    @Pattern(regexp = "$|[0-9]{12}", message = "Please enter a valid loanNumber")
    @Nullable
    private String loanNumber;

    @NotEmpty(message = "Mobile number should not be null or empty")
    @Pattern(regexp = "$|[0-9]{10}", message = "Please enter a valid mobileNumber")
    private String mobileNumber;

    @NotEmpty(message = "loanType should not be null or empty")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @NotNull
    private Integer totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @NotNull
    private Integer amountPaid;

    @Nullable
    private Integer outstandingAmount;
}
