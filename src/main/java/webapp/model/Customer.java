package webapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс клиента банка со свойствами
 * <b>customerId</b>, <b>fullName</b>, <b>phoneNumber</b>, <b>email</b>, <b>passportSeriesNumber</b>
 * <b>bankCardsList</b> и <b>accountsList</b>
 * @author Виктор Витевский
 * @version 1.0
 */
public class Customer {

    private Long customerId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Long passportSeriesNumber;
    private List<BankCard> bankCardsList = new ArrayList<>();
    private List<Account> accountsList  = new ArrayList<>();
    /**
     * Конструктор класса
     * @param fullName - ФИО клиента банка
     * @param phoneNumber - телефон клиента банка
     * @param email - email адрес клиента банка
     * @param passportSeriesNumber - паспортные данные клиента банка
     */
    public Customer(String fullName, String phoneNumber, String email, Long passportSeriesNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportSeriesNumber = passportSeriesNumber;
    }
    /**
     * @return Возврашает id клиента банка в формате Long
     */
    public Long getCustomerId() {
        return customerId;
    }
    /**
     * Метод устанавливает значение поля customerId
     * @param customerId - id клиента банка
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * @return Возврашает ФИО клиента банка в формате String
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * Метод устанавливает значение поля fullName
     * @param fullName - ФИО клиента банка
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * @return Возврашает номер телефона клиента банка в формате String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Метод устанавливает значение поля phoneNumber
     * @param phoneNumber - номер телефона клиента банка
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return Возврашает email адрес клиента банка в формате String
     */
    public String getEmail() {
        return email;
    }
    /**
     * Метод устанавливает значение поля email
     * @param email - email адрес клиента банка
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return Возврашает паспортные данные клиента банка в формате Long
     */
    public Long getPassportSeriesNumber() {
        return passportSeriesNumber;
    }
    /**
     * Метод устанавливает значение поля passportSeriesNumber
     * @param passportSeriesNumber - паспортные данные клиента банка
     */
    public void setPassportSeriesNumber(Long passportSeriesNumber) {
        this.passportSeriesNumber = passportSeriesNumber;
    }
    /**
     * @return Возврашает список банковских карт, привязанных к
     * клиенту банка в формате List<BankCard>
     */
    public List<BankCard> getBankCardsList() {
        return new ArrayList<>(bankCardsList);
    }
    /**
     * Метод устанавливает значение списка банковских карт, привязанных к
     * клиенту банка
     * @param bankCardsList - список банковских карт, привязанных к клиенту банка
     */
    public void setBankCardsList(List<BankCard> bankCardsList) {
        this.bankCardsList = bankCardsList;
    }
    /**
     * @return Возврашает список банковских счетов, привязанных к
     * клиенту банка в формате List<Account>
     */
    public List<Account> getAccountsList() {
        return new ArrayList<>(accountsList);
    }
    /**
     * Метод устанавливает значение списка банковских счетов, привязанных к
     * клиенту банка
     * @param accountsList - список банковских карт, привязанных к клиенту банка
     */
    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportSeriesNumber=" + passportSeriesNumber +
                ", bankCardsList=" + bankCardsList +
                ", accountsList=" + accountsList +
                '}';
    }
}
