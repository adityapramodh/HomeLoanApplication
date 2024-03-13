package main.loan.homeLoan.model;

import main.loan.homeLoan.records.Address;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Applicant {
    private String name;
    private LocalDate dob;
    private String gender;
    private Address currentAddress;
    private Address permanentAddress;
    private long annualIncome;
    private int age;
    private static Random random = new Random();

    public Applicant(String name, LocalDate dob, String gender, Address currentAddress, Address permanentAddress, long annualIncome, int age) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.annualIncome = annualIncome;
        this.age = age;
    }

    public static Applicant getRandomApplicant(long annualIncome,Address currentAddress, Address permanentAddress) {
        LocalDate dob = getRandomDate();
        return new Applicant(
                getRandomVal("Abel", "Alex", "Ben", "Ken", "Chris", "David", "Ethan", "Frank", "George", "Henry"),
                dob,
                getRandomVal("M", "F","U"),
                currentAddress,
                permanentAddress,
                annualIncome,
                LocalDate.now().getYear() - dob.getYear()
        );
    }

    public static String getRandomVal(String...data){
        return data[random.nextInt(data.length)];
    }

    public static LocalDate getRandomDate(){
        LocalDate startDate = LocalDate.of(1950, 1, 1);
        LocalDate endDate = LocalDate.of(2000, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(long annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "Applicant{"
                +"name='" + name + '\''
                +", dob= %d-%s-%d".formatted(dob.getDayOfMonth(), dob.getMonth(), dob.getYear())
                +", gender='" + gender + '\''
                +", currentAddress='" + currentAddress + '\''
                +", permanentAddress='" + permanentAddress + '\''
                +", annualIncome=$" + annualIncome
                +", age=" + age
                +'}';
    }
}
