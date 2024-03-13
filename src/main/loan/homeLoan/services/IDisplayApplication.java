package main.loan.homeLoan.services;

import main.loan.homeLoan.enums.ApplicationStatus;
import main.loan.homeLoan.model.Application;

import java.util.List;

public interface IDisplayApplication {
    /*
    * Display applications based on status.
    * */
    void displayApplicationStatus(List<Application> applicationList, ApplicationStatus status);

    /*
    * Default save to DataBase
    * */
    void deleteApplication(List<Application> processedApplications);
}
