package webapp.model;

import java.util.Random;

/**
 * Класс Банка со следующими константами
 * <b>BANK_IDENTIFIER</b>, <b>BANK_BIK</b>, <b>INDIVIDUAL_CARD_ACCOUNT</b>,
 * <b>DEMAND_DEPOSITS</b>, <b>CURRENCY_ACCOUNT_RUB</b>, <b>CURRENCY_ACCOUNT_USD</b> и <b>CURRENCY_ACCOUNT_EUR</b>
 * @author Виктор Витевский
 * @version 1.0
 */
public class Bank {

    private static final String BANK_IDENTIFIER = "46954";
    private static final String BANK_BIK = "043601607";
    private static final String INDIVIDUAL_CARD_ACCOUNT = "40817";
    private static final String DEMAND_DEPOSITS = "42301";
    private static final String CURRENCY_ACCOUNT_RUB = "810";
    private static final String CURRENCY_ACCOUNT_USD = "840";
    private static final String CURRENCY_ACCOUNT_EUR = "978";

    private static Long bankCardsCounter = 1L;
    /**
     * Метод генерирует новую банковскую карту
     * @param account - банковский счет к которому привязана карта
     * @return Возвращает созданную банковскую карту BankCard
     */
    public static BankCard cardGenerator(Account account){

        return new BankCard(cardNumberGenerator(), (int)(Math.random()*900 + 100),
                account.getAccountId(), account.getCustomerId());
    }
    /**
     * Метод генерирует новый банковсий счёт
     * @param customer - владелец банковского счёта
     * @return Возвращает созданный банковский счёт Account
     */
    public static Account accountGenerator(Customer customer){
        Account account;
        account = new Account(accountNumberGenerator((Math.random() < 0.8 ? 1 : 2),
                (Math.random() < 0.8 ? 1 : (Math.random() < 0.9 ? 2 : 3))), Math.random()*1000000,customer.getCustomerId()) ;

        return account;
    }
    /**
     * Метод генерирует номер банковской карты
     * @return Возвращает номер новой банковской карты в формате String
     */
    private static String cardNumberGenerator() {
        String cardNumber;
        String bin = PaymentSystem.values()[new Random().nextInt(PaymentSystem.values().length)].code + BANK_IDENTIFIER;
        String cardId = String.format("%09d", bankCardsCounter++);

        char[] numbers =  (bin + cardId).toCharArray();
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i] - '0';
            if (i%2 != 0) {
                sum += number;
            } else if (number*2 > 9) {
                sum += number*2 - 9;
            } else {
                sum += number*2;
            }
        }
        if (sum%10 == 0) {
            cardNumber = bin + cardId + 0;
        } else {
            cardNumber = bin + cardId + (10 - sum%10);
        }

        StringBuilder stringBuilder = new StringBuilder(cardNumber);
        stringBuilder.insert(4, "-");
        stringBuilder.insert(9, "-");
        stringBuilder.insert(14, "-");

        return stringBuilder.toString();
    }
    /**
     * Метод генерирует номер банковского счета клиета
     * @param i - тип банковского счёта
     * @param j - валюта счёта
     * @return Возвращает номер нового банковского счета в формате String
     */
    private static String accountNumberGenerator(int i, int j) {
        String accountNumber = "";

        if (i == 1) {
            accountNumber += INDIVIDUAL_CARD_ACCOUNT;
        } else {
            accountNumber += DEMAND_DEPOSITS;
        }

        if (j == 1) {
            accountNumber += CURRENCY_ACCOUNT_RUB;
        } else if (j == 2) {
            accountNumber += CURRENCY_ACCOUNT_USD;
        } else {
            accountNumber += CURRENCY_ACCOUNT_EUR;
        }

        String bankBranchNumber = (int) (Math.random() * 9000 + 1000) + "";
        StringBuilder cli = new StringBuilder();
        for (int l = 0; l < 7; l++) {
            cli.append((int) (Math.random() * 10));
        }
        accountNumber += 0 + bankBranchNumber + cli;

        char[] numbers = (BANK_BIK.substring(6) + accountNumber).toCharArray();
        int sum = 0;
        for (int k = 0; k < numbers.length; k++) {
            int number = numbers[i] - '0';
            if (k%3 == 0) {
                sum += number*7;
            } else if (k%3 == 1) {
                sum += number;
            } else {
                sum += number*3;
            }
        }

        numbers[11] = (char) ((sum%10 * 3) % 10 + '0');

        return new String(numbers).substring(3);
    }

    /**
     * Класс перечислений
     * Тип системы оплаты для банковской карты
     */
    enum PaymentSystem {
        MASTERCARD(5),
        VISA(4),
        MAESTRO(3),
        MIR(2);

        private final int code;

        PaymentSystem(int code) {
            this.code = code;
        }
    }
}
