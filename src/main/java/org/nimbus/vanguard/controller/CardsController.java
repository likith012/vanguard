package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Cards;
import org.nimbus.vanguard.repository.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @GetMapping("/cards")
    public List<Cards> getCards(@RequestParam long id) {
        return cardsRepository.findByCustomerId(id);
    }
}
