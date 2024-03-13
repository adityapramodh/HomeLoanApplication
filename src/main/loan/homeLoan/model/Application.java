package main.loan.homeLoan.model;

import main.loan.homeLoan.enums.ApplicationStatus;
import main.loan.homeLoan.enums.VerificationStatus;
import main.loan.homeLoan.records.Address;
import main.loan.homeLoan.records.Loans;
import main.loan.homeLoan.records.Document;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

public class Application {
    public static Random random = new Random();
    private String applicationNumber = "";
    private long requestedAmount;
    private long approvedAmount;
    private Applicant applicant;
    private OffsetDateTime createdDate;
    private ApplicationStatus currentStatus;
    private VerificationStatus documentStatus;
    private Loans loanType;
    private double interestRate;
    private List<Document> documents;

    public Application(Loans loanType, long requestedAmount, long annualIncome, Address currentAddress, Address permanentAddress, List<Document> documents) {
        this.applicationNumber = getRandomApplicationNumber();
        this.loanType = loanType;
        this.requestedAmount = requestedAmount;
        this.approvedAmount = loanCalculator(loanType, annualIncome, requestedAmount);
        this.applicant = Applicant.getRandomApplicant(annualIncome,currentAddress, permanentAddress);
        this.createdDate = OffsetDateTime.now();
        this.documents = documents;
        setStatus(ApplicationStatus.REVIEW_IN_PROCESS);
    }

    public String getRandomApplicationNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            applicationNumber += characters.charAt(index);
        }
        return applicationNumber;
    }

    public long loanCalculator(Loans type, long annualIncome, long requestedAmount) {
        double eligiblePercentage = switch (type.loanType()) {
            case "HBL" -> 0.6;
            case "HCL" -> 0.8;
            case "HEL" -> 0.5;
            default -> throw new IllegalArgumentException("Invalid loan type");
        };
        this.interestRate = eligiblePercentage;
        long loanAmount = (long) (annualIncome * eligiblePercentage);
        return Math.min(requestedAmount, loanAmount);
    }

    public static Application getRandomApplications(Loans loanType, long requestedAmount, long annualIncome, Address currentAddress, Address permanentAddress, List<Document> documents){
        return new Application(loanType, requestedAmount, annualIncome, currentAddress, permanentAddress, documents);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public long getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(long requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public long getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(long approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ApplicationStatus getStatus() {
        return currentStatus;
    }

    public void setStatus(ApplicationStatus status) {
        this.currentStatus = status;
    }

    public VerificationStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(VerificationStatus status) {
        this.documentStatus = status;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @Override
    public String toString() {
        return "\nApplication{" +
                "applicationNumber='" + applicationNumber + '\'' +
                ", loanType=" + loanType +
                ", interestRate=" + interestRate +
                ", requestedAmount=$" + requestedAmount +
                ", approvedAmount=$" + approvedAmount +
                ", applicant=" + applicant.getName() +
                ", createdDate=" + createdDate +
                ", docStatus=" + documentStatus +
                ", status=" + currentStatus +
                '}'+ "\n" + applicant +
                "\n" + documents;
    }
}
