package main.loan.homeLoan.services;

import main.loan.homeLoan.enums.VerificationStatus;
import main.loan.homeLoan.model.Application;
import main.loan.homeLoan.records.Document;

import java.util.List;

public interface ILoanProcess {
    /*
    * Collect and Add to the List of Docs
    * */
    void collectDocuments(List<Application> applications);

    /*
     * Approve or Reject the loan based on annual income
     * */
    void processLoan(List<Application> applications);

    /*
     * Check if collectedDocs list contains the mandatory Docs
     * */
    List<Application> verifyDocuments(List<Application> receivedApplications);


}
