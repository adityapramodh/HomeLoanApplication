package main.loan.homeLoan.application;

import main.loan.homeLoan.enums.ApplicationStatus;
import main.loan.homeLoan.model.Application;
import main.loan.homeLoan.records.Address;
import main.loan.homeLoan.records.Document;
import main.loan.homeLoan.records.Loans;
import main.loan.homeLoan.services.serviceImpl.DisplayApplications;
import main.loan.homeLoan.services.serviceImpl.LoanProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HouseLoanApplication {
    public static Random random = new Random();
    public static void main(String[] args) {
        /*
        1.Fill Applicant & Income Details
        2.View & Apply for Loan Offers
        3.Complete your Application
        4.Instant Approval - Show Approval Status
        * */

        //Create different types of loans to offer
        List<Loans> loans = new ArrayList<>(List.of(
                new Loans("HBL","House Basic Loan"),
                new Loans("HCL", "House Construction Loan"),
                new Loans("HEL", "House Extension Loan")
        ));

        /*
        * Create Loan Applications
        * */
        List<Application> applications = Stream.generate(()->Application.getRandomApplications(
                loans.get(random.nextInt(loans.size())),
                random.nextInt(10000, 100000),
                random.nextInt(10000, 100000),
                Address.generateRandomAddress(),
                        Address.generateRandomAddress(),
                        Document.getRandomDocument()))
                .limit(100)
                .toList();

        /*
        * Process Loan Applications
        * */
        LoanProcess loanProcess = new LoanProcess();
        loanProcess.processLoan(applications);

        /*
        * Display Loan Details
        * */
        DisplayApplications display = new DisplayApplications();

        // Display Received Applications
        System.out.println("\nReceived Applications: " + (long) loanProcess.receivedApplications.size());
//        display.displayApplicationStatus(loanProcess.receivedApplications, ApplicationStatus.REVIEW_IN_PROCESS);

        // Display Approved Applications
        System.out.println("\nApproved Applications: " + (long) loanProcess.approvedApplications.size());
//        display.displayApplicationStatus(loanProcess.approvedApplications, ApplicationStatus.APPROVED);

        // Display Rejected Applications
        System.out.println("\nRejected Applications: " + (long) loanProcess.rejectedApplications.size());
//        display.displayApplicationStatus(loanProcess.rejectedApplications, ApplicationStatus.REJECTED);

        // Delete Rejected Applications
        System.out.println("\nAll the " + (loanProcess.processedApplications.stream()
                .filter(s->s.getStatus().equals(ApplicationStatus.REJECTED))
                .count()) + " rejected accounts are being deleted...");
        System.out.println();
        display.displayApplicationStatus(loanProcess.processedApplications, ApplicationStatus.REJECTED);
        display.deleteApplication(loanProcess.processedApplications);

        // Display Remaining/Approved Applications
        System.out.printf("%nHere are the %d Approved Applications: %n",
                loanProcess.processedApplications.size());
        loanProcess.processedApplications.forEach(System.out::println);
    }
}
