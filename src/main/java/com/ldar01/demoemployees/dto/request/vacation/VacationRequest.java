package com.ldar01.demoemployees.dto.request.vacation;

import com.ldar01.demoemployees.validators.EndBeforeStartDate;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@EndBeforeStartDate
public class VacationRequest {
    @NotNull(message = "Start date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull(message = "Reason cannot be null")
    private String reason;

    @NotNull(message = "Employee id cannot be null")
    private int employeeId;
}
