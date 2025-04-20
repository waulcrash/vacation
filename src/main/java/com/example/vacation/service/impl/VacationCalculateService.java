package com.example.vacation.service.impl;

import org.springframework.stereotype.Service;

import com.example.vacation.service.IVacationCalculateService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationCalculateService implements IVacationCalculateService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private static final Set<LocalDate> HOLIDAYS = initHolidays();

    @Override
    public double getPay(double averageSalary, int vacationDays, String startDayOfMounths) { //c учетом начала отпуска 
        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be positive"); //дней отпуска должны быть +
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); //формат даты другой
        LocalDate startDate = LocalDate.parse(startDayOfMounths, formatter);
        
        int workingDays = calculateWorkingDays(startDate, vacationDays); //кол-во рабочих дней
        return getPay(averageSalary, workingDays);
    }

    @Override
    public double getPay(double averageSalary, int vacationDays) {  //без учета дня отпуска
        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be positive");
        }
        
        double dailySalary = averageSalary / AVERAGE_DAYS_IN_MONTH;
        return Math.round(dailySalary * vacationDays * 100) / 100.0;
    }

    private int calculateWorkingDays(LocalDate startDate, int vacationDays) { //кол-во раб.дней
        int workingDays = 0;
        LocalDate currentDate = startDate;
        
        for (int i = 0; i < vacationDays; i++) {
            if (!isWeekend(currentDate) && !isHoliday(currentDate)) { //счетчик если не выходной и не праздник
                workingDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        
        return workingDays;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6; // суббота и воскресенье
    }

    private boolean isHoliday(LocalDate date) {
        LocalDate holidayDate = date.withYear(2025); // фикс год
        return HOLIDAYS.contains(holidayDate);
    }

    private static Set<LocalDate> initHolidays() {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(LocalDate.of(2025, 1, 1)); 
        holidays.add(LocalDate.of(2025, 1, 2));
        holidays.add(LocalDate.of(2025, 1, 3));
        holidays.add(LocalDate.of(2025, 1, 4));
        holidays.add(LocalDate.of(2025, 1, 5));
        holidays.add(LocalDate.of(2025, 1, 6));
        holidays.add(LocalDate.of(2025, 1, 7));
        holidays.add(LocalDate.of(2025, 1, 8));
        holidays.add(LocalDate.of(2025, 2, 23)); 
        holidays.add(LocalDate.of(2025, 3, 8)); 
        holidays.add(LocalDate.of(2025, 5, 1)); 
        holidays.add(LocalDate.of(2025, 5, 9)); 
        holidays.add(LocalDate.of(2025, 6, 12));
        holidays.add(LocalDate.of(2025, 11, 4)); 
        
        return holidays;
    }
}