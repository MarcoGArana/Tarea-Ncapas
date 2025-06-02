package com.ldar01.demoemployees.dto.request.vacation;

import com.ldar01.demoemployees.utils.enums.StatusType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VacationUpdateRequest {
    @NotNull(message = "You must provide an vacation ID")
    private Integer vacationId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private StatusType status;
    private int employeeId;
}
