package ch.ipt.JpaSwaggerLearning.service.Card;

import ch.ipt.JpaSwaggerLearning.mappers.CardMapper;
import ch.ipt.JpaSwaggerLearning.model.CardEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardDTO;
import ch.ipt.JpaSwaggerLearning.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public List<CardDTO> getCards(){
        List<CardEntity> cardEntities = cardRepository.findAll();
        List<CardDTO> cardDTOs = cardEntities.stream()
                .map(entity -> new CardDTO(entity.getId(), entity.getUUID(), entity.getCardNumber())).collect(Collectors.toList());

        return cardDTOs;
    }

    public Integer createCard(CardCreateDTO cardCreateDTO){
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardCreateDTO.getCardNumber());
        cardRepository.save(cardEntity);

        return cardEntity.getId();
    }

    public CardDTO getCard(int cardId) {
        CardEntity cardEntity = cardRepository.getReferenceById(cardId);
        return cardMapper.mapCardEntityToCardDTO(cardEntity);
    }

}
