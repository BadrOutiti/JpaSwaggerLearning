package ch.ipt.JpaSwaggerLearning.mappers;

import ch.ipt.JpaSwaggerLearning.model.CardEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardDTO;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDTO MapCardEntityToCardDTO(CardEntity cardEntity){
        return new CardDTO(cardEntity.getUUID(), cardEntity.getCardNumber());
    }
}
