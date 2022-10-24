package ru.netology.tourPayment.test;

import org.junit.jupiter.api.*;
import ru.netology.tourPayment.data.DataHelper;
import ru.netology.tourPayment.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentsOnCardTest extends BaseTest{


    @Test
    @DisplayName("Покупка разрешенной картой. Отображается сообщение об отказе банком")
    void shouldRejectionMessageIfPaymentOfCard() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCardPage = mainPage.paymentByCard();

        paymentOnCardPage.pay(DataHelper.getDeclinedCardInfo());

        paymentOnCardPage.shouldNotificationMessage("Банк отказал в проведении операции");
    }


    @Test
    @DisplayName("Покупка картой отсутствующей в системе. Отображается сообщения об отказе банкам.")
    void shouldNotificationMsgIfCardFailed(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCardPage = mainPage.paymentByCard();

        paymentOnCardPage.pay(DataHelper.getFiledCardInfo());

        paymentOnCardPage.shouldNotificationMessage("Банк отказал в проведении операции");
    }



}
