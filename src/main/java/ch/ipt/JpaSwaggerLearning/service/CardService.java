package ch.ipt.JpaSwaggerLearning.service;

import ch.ipt.JpaSwaggerLearning.model.CardEntity;
import ch.ipt.JpaSwaggerLearning.openapi.card.model.CardCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.card.model.CardDTO;
import ch.ipt.JpaSwaggerLearning.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<CardDTO> getCards(){
        List<CardEntity> cardEntities = cardRepository.findAll();
        List<CardDTO> cardDTOs = cardEntities.stream()
                .map(entity -> new CardDTO(entity.getUUID(), entity.getCardNumber())).collect(Collectors.toList());

        return cardDTOs;
    }

    public Integer createCard(CardCreateDTO cardCreateDTO){
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardCreateDTO.getCardNumber());
        cardRepository.save(cardEntity);

        return cardEntity.getId();
    }
}
