package webapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Класс банковской карты со свойствами
 * <b>cardId</b>, <b>cardNumber</b>, <b>cvc2cvv2</b>, <b>accountId</b> и <b>customerId</b>
 * @author Виктор Витевский
 * @version 1.0
 */
@JsonAutoDetect
public class BankCard {

    private Long cardId;
    private final String cardNumber;
    @JsonIgnore
    private final int cvc2cvv2;
    @JsonIgnore
    private final Long accountId;
    @JsonIgnore
    private final Long customerId;
    /**
     * Конструктор класса
     * @param cardNumber - номер банковской карты
     * @param cvc2cvv2 - секретный код
     * @param accountId - id банковского счета, к которому привязана карта
     * @param customerId - id владельца банковской карты
     */
    public BankCard(String cardNumber, int cvc2cvv2, Long accountId, Long customerId) {
        this.cardNumber = cardNumber;
        this.cvc2cvv2 = cvc2cvv2;
        this.accountId = accountId;
        this.customerId = customerId;
    }
    /**
     * @return Возврашает id банковской карты в формате Long
     */
    public Long getCardId() {
        return cardId;
    }
    /**
     * Метод устанавливает значение поля cardId
     * @param cardId - id банковской карты
     */
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    /**
     * @return Возврашает номер банковской карты в формате String
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @return Возврашает id номера счета, к которому привязана
     * банковская карта в формате Long
     */
    public Long getAccountId() {
        return accountId;
    }
    /**
     * @return Возврашает секретный код банковской карты в формате int
     */
    public int getCvc2cvv2() {
        return cvc2cvv2;
    }
    /**
     * @return Возврашает id владельца банковской карты в формате Long
     */
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvc2cvv2=" + cvc2cvv2 +
                ", accountId=" + accountId +
                ", customerId=" + customerId +
                '}';
    }
}