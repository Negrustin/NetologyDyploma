package ru.netology.tourPayment.test;

import org.junit.jupiter.api.*;
import ru.netology.tourPayment.data.DataHelper;
import ru.netology.tourPayment.data.SQLHelper;
import ru.netology.tourPayment.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentOnCreditTest extends BaseTest{





    @Test
    @DisplayName("Покупка в кредит разрешенной картой, статус операции в БД 'APPROVED'")
    void shouldStatusCodeIfPaymentOnCredit() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCreditPage = mainPage.PaymentByCredit();

        paymentOnCreditPage.pay(DataHelper.getApprovedCardInfo());

        paymentOnCreditPage.shouldNotificationMessage("Операция одобрена Банком.");
        var statusCode = SQLHelper.getPaymentByCreditStatusCode();

        String expected = "APPROVED";
        String actual = statusCode;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Покупка в кредит запрещённой  картой, отображается сообщение об отказе в операции банком")
    void shouldRejectionMessageIfPaymentOfCredit() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCreditPage = mainPage.PaymentByCredit();

        paymentOnCreditPage.pay(DataHelper.getDeclinedCardInfo());

        paymentOnCreditPage.shouldNotificationMessage("Банк отказал в проведении операции");
    }
    @Test
    @DisplayName("Покупка в кредит, корректность записи ID в таблицу order_entity")
    void ShouldCorrectAddBankIdToDateBase(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCreditPage = mainPage.PaymentByCredit();
        paymentOnCreditPage.pay(DataHelper.getApprovedCardInfo());
        paymentOnCreditPage.shouldNotificationMessage();

        var creditId = SQLHelper.getPaymentByCreditBankID();
        var credit_idFromOrders = SQLHelper.getBankIdFromOrders();

        String expected = creditId;
        String actual = credit_idFromOrders;

        Assertions.assertEquals(expected,actual);


    }

}
