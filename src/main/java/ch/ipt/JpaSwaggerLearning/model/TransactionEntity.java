package ch.ipt.JpaSwaggerLearning.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amountInChf;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity cardEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmountInChf() {
        return amountInChf;
    }

    public void setAmountInChf(Integer amountInChf) {
        this.amountInChf = amountInChf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }
}
