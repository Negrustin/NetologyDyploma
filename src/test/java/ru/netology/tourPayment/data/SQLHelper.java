package ru.netology.tourPayment.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private SQLHelper() {
    }

    private static QueryRunner runner = new QueryRunner();
    static String dbUrl = System.getProperty("db.url");
    static String dbUser = System.getProperty("db.user");
    static String dbPassword = System.getProperty("db.password");
    private static Connection conn = getConn();

    @SneakyThrows
    private static Connection getConn() {

        return DriverManager.getConnection(
                dbUrl,
                dbUser,
                dbPassword
        );
    }
//Payments by card requests
    @SneakyThrows
    public static String getPaymentByCardStatusCode() {
        var codeSQl = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQl, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentByCardTransactionId() {
        var codeSQL = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static int getPaymentAmount() {
        var codeSQL = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentIdFromOrders() {
        var codeSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());

    }
//Payments by credit requests
    @SneakyThrows
    public static String getPaymentByCreditStatusCode() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }
    @SneakyThrows
    public static String getPaymentByCreditBankID(){
        var codeSQL = "SELECT bank_id from credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }
    @SneakyThrows
    public static String getBankIdFromOrders(){
        var codeSQL = "SELECT credit_id FROM order_entity ORDER BY created DESC  LIMIT 1;";
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }


    @SneakyThrows
    public static void cleanDatabase() {
        runner.execute(conn, "DELETE FROM credit_request_entity;");
        runner.execute(conn, "DELETE FROM order_entity;");
        runner.execute(conn, "DELETE FROM payment_entity;");
    }


}
