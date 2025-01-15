package ch.ipt.JpaSwaggerLearning.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "t_card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uuid;

    private String cardNumber;

    public CardEntity(){
        if (this.uuid == null) { // Only set UUID if it is null
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
