package webapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс банковского счета со свойствами
 * <b>balance</b> <b>accountNumber</b>, <b>balance</b>, <b>customer</b> и <b>cardList</b>
 * @author Виктор Витевский
 * @version 1.0
 */
@JsonAutoDetect
public class Account {

    private Long accountId;
    private final String accountNumber;
    private Double balance;
    @JsonIgnore
    private Long customerId;
    @JsonIgnore
    private List<BankCard> cardList;

    /**
     * Конструктор класса
     * @param accountNumber - номер банковского счёта
     * @param balance - остаток денежных средств на счёте
     * @param customerId - id владельца банковского счёта
     */
    public Account(String accountNumber, Double balance, Long customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }
    /**
     * @return Возвращает id банковского счёта в формате Long
     */
    public Long getAccountId() {
        return accountId;
    }
    /**
     * Метод устанавливает значение поля accountId
     * @param accountId - id банковского счёта
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    /**
     * @return Возврашает номер банковского счёта в формате String
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    /**
     * @return Возврашает баланс банковского счёта в формате double
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Метод устанавливает значение остатка денежных средств на счёте
     * @param balance - остаток денежных средств на счёте
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    /**
     * @return Возврашает id владельца банковского счёта в формате Long
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Метод устанавливает значение id владельца банковского счёта
     * @param customerId - id владельца банковского счёта
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * @return Метод возвращает список банковских карт, привязанных к
     * банковсому счёту в формате List<BankCard>
     */
    public List<BankCard> getCardList() {
        return new ArrayList<>(cardList);
    }
    /**
     * Метод устанавливает значение списка банковских карт, привязанных к
     * банковсому счёту
     * @param cardList - список банковских карт, привязанных к банковсому счёту
     */
    public void setCardList(List<BankCard> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", customerId='" + customerId + '\'' +
                ", cardList=" + cardList +
                '}';
    }
}