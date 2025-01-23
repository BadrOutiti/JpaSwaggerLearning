package ch.ipt.JpaSwaggerLearning.mappers;

import ch.ipt.JpaSwaggerLearning.model.AccountEntity;
import ch.ipt.JpaSwaggerLearning.model.CardEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public AccountDTO mapAccountEntityToAccountDTO(AccountEntity accountEntity) {
        UserDTO userDTO = new UserDTO(accountEntity.getUser().getId(), accountEntity.getUser().getName());
        List<CardDTO> cards = new ArrayList<>();
        for (CardEntity card : accountEntity.getCards()) {
            CardDTO cardDTO = new CardDTO(card.getId(), card.getUUID(), card.getCardNumber());
            cards.add(cardDTO);
        }
        return new AccountDTO(accountEntity.getId(), userDTO, cards);
    }
}
