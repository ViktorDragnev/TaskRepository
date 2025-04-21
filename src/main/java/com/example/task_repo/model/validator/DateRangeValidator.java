package com.example.task_repo.model.validator;


import com.example.task_repo.model.dto.request.FilterReportDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *  Добавено в лабораторно упражнение 8
 */
public class DateRangeValidator implements
        ConstraintValidator<ValidDateRange, FilterReportDto> {

    @Override
    public boolean isValid(FilterReportDto dto,
                           ConstraintValidatorContext context) {
        if (dto.getFrom() == null || dto.getTo() == null) {
            return true; // @NotNull ще хване това
        }
        return dto.getTo().isAfter(dto.getFrom());
    }
}