package ch.ipt.JpaSwaggerLearning.service;


import ch.ipt.JpaSwaggerLearning.openapi.api.CardApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class CardApiDelegateImpl implements CardApiDelegate {
    @Autowired
    private CardService cardService;

    @Override
    public ResponseEntity<Void> createCard(CardCreateDTO cardCreateDTO){
        // Create the user using the service
        Integer cardId = cardService.createCard(cardCreateDTO);

        // Construct the URI for the created resource
        URI location = URI.create(String.format("/users/%d", cardId));

        // Return 201 Created with Location header
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<CardDTO>> listCards(){
        return ResponseEntity.ok(cardService.getCards());
    }

}
