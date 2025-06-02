package com.ldar01.demoemployees.dto.response.vacation;

import com.ldar01.demoemployees.utils.enums.StatusType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VacationResponse {
    private Integer vacationId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private StatusType status;
    private int employeeId;
}
