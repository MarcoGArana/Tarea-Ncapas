package com.ldar01.demoemployees.validators;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndBeforeStartDateValidator implements ConstraintValidator<EndBeforeStartDate, VacationRequest> {
    @Override
    public void initialize(EndBeforeStartDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(VacationRequest vacationRequest, ConstraintValidatorContext constraintValidatorContext) {
        return !vacationRequest.getEndDate().before(vacationRequest.getStartDate());
    }
}
