package com.example.vacation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vacation.service.IVacationCalculateService;

@RestController
@RequestMapping("/api")
public class VacationPayCalculate {

    private final IVacationCalculateService _calculateService;

    public VacationPayCalculate(IVacationCalculateService calculateService) {
        _calculateService = calculateService;
    }

    @GetMapping("/calculate")
    public String getCalculatedValue(@RequestParam double averageSalary, @RequestParam int vacationDays, @RequestParam(required = false) String startDayOfMounths) {
        if (startDayOfMounths != null) {
            return String.valueOf(_calculateService.getPay(averageSalary, vacationDays, startDayOfMounths));
        } else {
            return String.valueOf(_calculateService.getPay(averageSalary, vacationDays));
        }
    }
}