package com.example.vacation.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VacationCalculateServiceTest {
   private final VacationCalculateService service = new VacationCalculateService();

    final int averageSalary = 85000;
    final int vacationDays = 14;
    final double vacationFactor = 29.3;

    @Test
    void calculateWithoutDate() {
        // Ручной расчет: 85000 / 29.3 * 14 = 40614.33
        double expectation = Math.round(averageSalary/vacationFactor*14*100)/100.0;
        double result = service.getPay(averageSalary, vacationDays);
        assertEquals(expectation, result, 0.01, "Неверный расчет для 85000 руб и 14 дней");
    }

    @Test
    void calculateWithDate() {
        double expectation = Math.round(averageSalary/vacationFactor*9*100)/100.0;
        double result = service.getPay(averageSalary, vacationDays, "01.06.2025");
        assertEquals(expectation, result, 0.01, "Неверный расчет с учетом выходных");
    }

    @Test
    void calculateWithDateWithoutHollidays(){
        double expectation = Math.round(averageSalary/vacationFactor*10*100)/100.0;
        double result = service.getPay(averageSalary, vacationDays, "01.04.2025");  
        assertEquals(expectation, result, 0.01, "Неверный расчет для 85000 руб и 14 дней");
    }

}
