package ru.netology.tourPayment.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class DataHelper {
    private static final String APPROVED_CARD_NUMBER = "4444444444444441";
    private static final String DECLINED_CARD_NUMBER = "4444444444444442";
    private static Faker faker = new Faker(new Locale("EN"));

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(
                APPROVED_CARD_NUMBER,
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomFullName(),
                generateRandomCVV());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(
                DECLINED_CARD_NUMBER,
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomFullName(),
                generateRandomCVV()
        );
    }


    public static CardInfo getFiledCardInfo() {
        return new CardInfo(
                generateRandomCardNumber(),
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomFullName(),
                generateRandomCVV()
        );

    }
    public static CardInfo getCyrillicCardInfo(){
         Faker faker = new Faker(new Locale("ru"));
         return new CardInfo(
                generateRandomCardNumber(),
                generateRandomMonth(),
                generateRandomYear(),
                faker.name().fullName(),
                generateRandomCVV()
        );

    }

    public static CardInfo getEmptyCardNumberCardInfo() {
        return new CardInfo(
                "",
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomFullName(),
                generateRandomCVV()
        );
    }

    public static CardInfo getEmptyMonthCardInfo() {
        return new CardInfo(
                APPROVED_CARD_NUMBER,
                "",
                generateRandomYear(),
                generateRandomFullName(),
                generateRandomCVV()
        );
    }

    public static CardInfo getEmptyYearCardInfo() {
        return new CardInfo(
                APPROVED_CARD_NUMBER,
                generateRandomMonth(),
                "",
                generateRandomFullName(),
                generateRandomCVV()
        );
    }

    public static CardInfo getEmptyNameCardInfo() {
        return new CardInfo(
                APPROVED_CARD_NUMBER,
                generateRandomMonth(),
                generateRandomYear(),
                "",
                generateRandomCVV()
        );
    }

    public static CardInfo getEmptyCVVCardInfo() {
        return new CardInfo(
                APPROVED_CARD_NUMBER,
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomFullName(),
                ""
        );
    }


    private static String generateRandomFullName() {

        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateRandomCardNumber() {
        return faker.numerify("################");
    }


    public static String generateRandomCVV() {
        return faker.numerify("###");
    }


    private static String generateRandomMonth() {
        YearMonth now = YearMonth.now();
        int randomNumber = ThreadLocalRandom.current().nextInt(61);
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        YearMonth randomDate = now.plusMonths(randomNumber);
        String randomMonth = randomDate.format(month);
        return randomMonth;
    }

    private static String generateRandomYear() {
        YearMonth now = YearMonth.now();
        int randomNumber = ThreadLocalRandom.current().nextInt(61);
        YearMonth randomDate = now.plusMonths(randomNumber);
        String randomYear = Integer.toString(randomDate.getYear());
        randomYear = randomYear.substring(2, 4);
        return randomYear;
    }

    public static BadNumericFormat getBadFormatNumericField() {
        return new BadNumericFormat(faker.numerify("#"));
    }

    @Value
    public static class BadNumericFormat {
        private String number;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvv;
    }


}
