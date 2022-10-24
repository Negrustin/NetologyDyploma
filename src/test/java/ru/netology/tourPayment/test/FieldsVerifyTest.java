package ru.netology.tourPayment.test;


import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.netology.tourPayment.data.DataHelper;
import ru.netology.tourPayment.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldsVerifyTest extends BaseTest {
    private String cardNumberFieldName = "Номер карты";
    private String monthFieldName = "Месяц";
    private String yearFieldName = "Год";
    private String ownerFieldName = "Владелец";
    private String cvvFieldName = "CVC/CVV";

    @Test
    @DisplayName("Оплата разрешённой картой, Пустое поле номера карты")
    void showErrorMsgIfCardNumberFieldIsEmpty() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var cardPaymentPage = mainPage.paymentByCard();

        cardPaymentPage.pay(DataHelper.getEmptyCardNumberCardInfo());


        assertEquals(
                "Поле обязательно для заполнения",
                cardPaymentPage.shouldFiledSubMessage(cardNumberFieldName));
    }

    @Test
    @DisplayName("Оплата  картой, Пустое месяца")
    void shouldEmptyErrorMessageIfMonthFieldIsEmpty() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var cardPaymentPage = mainPage.paymentByCard();

        cardPaymentPage.pay(DataHelper.getEmptyMonthCardInfo());

        assertEquals(
                "Поле обязательно для заполнения",
                cardPaymentPage.shouldFiledSubMessage(monthFieldName));

    }

    @Test
    @DisplayName("Кредит по данным карты, пустое поле года")
    void shouldEmptyErrorMessageIfYearFieldIsEmpty() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCreditPage = mainPage.PaymentByCredit();

        paymentOnCreditPage.pay(DataHelper.getEmptyYearCardInfo());

        assertEquals(
                "Поле обязательно для заполнения",
                paymentOnCreditPage.shouldFiledSubMessage(yearFieldName));
    }

    @Test
    @DisplayName("Кредит по данным карты, пустое поле имени")
    void shouldEmptyErrorMessageIfOwnerFieldIsEmpty() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCreditPage = mainPage.PaymentByCredit();

        paymentOnCreditPage.pay(DataHelper.getEmptyNameCardInfo());

        assertEquals(
                "Поле обязательно для заполнения",
                paymentOnCreditPage.shouldFiledSubMessage(ownerFieldName));
    }

    @Test
    @DisplayName("Оплата  картой, Пустое поле CVV/CVC")
    void shouldEmptyErrorMessageIfCVVFieldIsEmpty() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var cardPaymentPage = mainPage.paymentByCard();

        cardPaymentPage.pay(DataHelper.getEmptyCVVCardInfo());

        assertEquals(
                "Поле обязательно для заполнения",
                cardPaymentPage.shouldFiledSubMessage(cvvFieldName));
    }

    @Test
    @DisplayName("При вводе в числовые поля данных в неверном формате отображается сообщение 'Неверный формат'")
    void shouldBadFormatMessageIfSetBadFormatValue(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var cardPaymentPage = mainPage.paymentByCard();

        cardPaymentPage.badFormatNumericField(DataHelper.getBadFormatNumericField());

        assertEquals("Неверный формат",cardPaymentPage.shouldFiledSubMessage(cardNumberFieldName));
        assertEquals("Неверный формат",cardPaymentPage.shouldFiledSubMessage(monthFieldName));
        assertEquals("Неверный формат",cardPaymentPage.shouldFiledSubMessage(monthFieldName));
        assertEquals("Неверный формат",cardPaymentPage.shouldFiledSubMessage(cvvFieldName));
    }

    @Test
    @DisplayName("Оплата по карте с именем введенным кириллицей")
    void ShouldErrorMessageIfSetNameCyrillic(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCardPage = mainPage.paymentByCard();

        paymentOnCardPage.pay(DataHelper.getCyrillicCardInfo());

        assertEquals("Неверный формат",paymentOnCardPage.shouldFiledSubMessage(ownerFieldName));
    }
}
