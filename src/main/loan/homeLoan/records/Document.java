package main.loan.homeLoan.records;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Document(String docType, String docValue) {
    public static Random random = new Random();

    public static List<Document> getRandomDocument(){
        return new ArrayList<>(List.of(
                new Document("PAN", getRandomPAN()),
                new Document("ITR", getRandomItrForm()),
                new Document("AADHAR", getRandomAadhaar())
        ));
    }

    public static String getRandomVal(String...data){
        return data[random.nextInt(data.length)];
    }

    private static String getRandomString(int length, String characters) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String getRandomItrForm() {
        return getRandomVal("","ITR 1", "ITR 2", "ITR 3", "ITR 4", "ITR 5", "ITR 6", "ITR 7");
    }

    public static String getRandomPAN() {
        return getRandomVal("", getRandomString(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"));
    }

    public static String getRandomAadhaar() {
        return getRandomVal("", getRandomString(12, "1234567890"));
    }

    @Override
    public String toString() {
        return "Documents{" +
                "docType='" + docType + '\'' +
                ", docValue='" + docValue + '\'' +
                '}';
    }
}
