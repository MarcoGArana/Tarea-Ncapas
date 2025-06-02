package com.ldar01.demoemployees.utils.mappers;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.entities.Employee;
import com.ldar01.demoemployees.entities.Vacation;
import com.ldar01.demoemployees.utils.enums.StatusType;

import java.util.List;

public class VacationMapper {
    public static Vacation toEntityCreate(VacationRequest vacation, Employee employee){
        return Vacation.builder()
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .reason(vacation.getReason())
                .status(StatusType.PENDING)
                .employee(employee)
                .build();
    }

    public static Vacation toEntityUpdate(VacationUpdateRequest vacation, Employee employee){
        return Vacation.builder()
                .id(vacation.getVacationId())
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .reason(vacation.getReason())
                .status(vacation.getStatus())
                .employee(employee)
                .build();
    }

    public static VacationResponse toDTO(Vacation vacation) {
        return VacationResponse.builder()
                .vacationId(vacation.getId())
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .status(vacation.getStatus())
                .reason(vacation.getReason())
                .employeeId(vacation.getEmployee().getId())
                .build();
    }

    public static List<VacationResponse> toDTOList(List<Vacation> vacations) {
        return vacations.stream()
                .map(VacationMapper::toDTO)
                .toList();
    }
}
