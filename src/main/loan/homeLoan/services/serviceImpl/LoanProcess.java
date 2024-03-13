package main.loan.homeLoan.services.serviceImpl;

import main.loan.homeLoan.enums.ApplicationStatus;
import main.loan.homeLoan.enums.VerificationStatus;
import main.loan.homeLoan.model.Application;
import main.loan.homeLoan.records.Document;
import main.loan.homeLoan.services.ILoanProcess;

import java.util.List;
import java.util.stream.Collectors;

public class LoanProcess implements ILoanProcess {
    public List<Application> receivedApplications;
    public List<Application> processedApplications;
    public List<Application> approvedApplications;
    public List<Application> rejectedApplications;
    public List<List<Document>> collectedDocuments;

    @Override
    public void collectDocuments(List<Application> applications) {
        this.collectedDocuments = applications.stream()
                .map(Application::getDocuments)
                .collect(Collectors.toList());
    }

    @Override
    public void processLoan(List<Application> applications) {

        //Received Applications
        receivedApplications = applications.stream()
                .peek((s)->s.setStatus(ApplicationStatus.REVIEW_IN_PROCESS))
                .toList();

        //Collect received applications for document verification
//        collectDocuments(receivedApplications);

        //Set Application Status
        processedApplications = verifyDocuments(receivedApplications).stream()
                .filter((s)->s.getDocumentStatus().equals(VerificationStatus.SUCCESSFUL))
                .peek((s)->{
                    if(s.getRequestedAmount()<=s.getApprovedAmount()) {
                        s.setStatus(ApplicationStatus.APPROVED);
                    } else{
                        s.setStatus(ApplicationStatus.REJECTED);
                    }
                })
                .collect(Collectors.toList());

        //Collect Approved Applications
        approvedApplications = processedApplications.stream()
                .filter(s->s.getStatus().equals(ApplicationStatus.APPROVED))
                .collect(Collectors.toList());

        //Collect Rejected Applications
        rejectedApplications = processedApplications.stream()
                .filter(s->s.getStatus().equals(ApplicationStatus.REJECTED))
                .collect(Collectors.toList());
    }

    // Need to be Worked On
    @Override
    public List<Application> verifyDocuments(List<Application> receivedApplications) {
        return receivedApplications.stream()
                .peek(s->{
                    // if docValue is empty
                    if(s.getDocuments().isEmpty()){
                        s.setDocumentStatus(VerificationStatus.UNSUCCESSFUL);
                    } else {
                        s.setDocumentStatus(VerificationStatus.SUCCESSFUL);
                    }
                })
                .toList();
    }
}
