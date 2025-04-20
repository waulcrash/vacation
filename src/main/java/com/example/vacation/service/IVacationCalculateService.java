package com.example.vacation.service;

public interface IVacationCalculateService {

    double getPay(double averageSalary, int vacationDays, String startDayOfMounths);
    double getPay(double averageSalary, int vacationDays);
} 