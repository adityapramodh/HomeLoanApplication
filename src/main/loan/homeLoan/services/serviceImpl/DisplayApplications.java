package main.loan.homeLoan.services.serviceImpl;

import main.loan.homeLoan.enums.ApplicationStatus;
import main.loan.homeLoan.model.Application;
import main.loan.homeLoan.services.IDisplayApplication;

import java.util.List;

public class DisplayApplications implements IDisplayApplication {
    @Override
    public void displayApplicationStatus(List<Application> applicationList, ApplicationStatus status) {
        applicationList.stream()
                .filter((s)->s.getStatus().equals(status))
                .toList()
                .forEach(System.out::println);
    }

    @Override
    public void deleteApplication(List<Application> processedApplications) {
            processedApplications
                    .removeIf(i->i.getStatus().equals(ApplicationStatus.REJECTED));
    }
}
