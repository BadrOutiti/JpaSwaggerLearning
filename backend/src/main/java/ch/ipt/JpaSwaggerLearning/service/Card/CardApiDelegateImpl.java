package ch.ipt.JpaSwaggerLearning.service.Card;


import ch.ipt.JpaSwaggerLearning.openapi.api.CardApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class CardApiDelegateImpl implements CardApiDelegate {

    private final CardService cardService;

    public CardApiDelegateImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public ResponseEntity<CardDTO> createCard(CardCreateDTO cardCreateDTO) {
        // Create the user using the service
        Integer cardId = cardService.createCard(cardCreateDTO);

        //TODO: Dont use hardcoded string
        URI location = URI.create("http://localhost:8080/card/" + cardId);

        return ResponseEntity.created(location).body(cardService.getCard(cardId));
    }

    @Override
    public ResponseEntity<List<CardDTO>> listCards(){
        return ResponseEntity.ok(cardService.getCards());
    }

}
