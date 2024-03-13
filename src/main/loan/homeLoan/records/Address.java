package main.loan.homeLoan.records;

import java.util.Random;

public record Address(String houseNo, String city, String state, String zipcode) {
    public static Random random = new Random();

    public static String getRandomVal(String...data){
        return data[random.nextInt(data.length)];
    }

    public static Address generateRandomAddress(){
        return new Address(
                getRandomVal("1-2/3", "3-2/1", "4-3/1", "5-6/7"),
                getRandomVal("HYD", "CHE", "VSP", "DEL"),
                getRandomVal("TS", "AP", "TN", "DEL"),
                getRandomVal("533002", "530001", "533005","500013"));
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNo='" + houseNo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
