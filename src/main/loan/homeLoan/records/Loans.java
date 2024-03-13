package main.loan.homeLoan.records;
/*
* House Basic Loan, House Construction Loan, House Extension Loan.
* */
public record Loans(String loanType, String loanTiTle) {
    @Override
    public String toString() {
        return loanTiTle;
    }
}
