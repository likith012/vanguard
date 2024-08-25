package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Cards;
import org.nimbus.vanguard.model.Customer;
import org.nimbus.vanguard.repository.CardsRepository;
import org.nimbus.vanguard.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/cards")
    public List<Cards> getCards(@RequestParam String email) {
        Long id = customerRepository.findByEmail(email).map(Customer::getId).orElse(null);
        if (id != null) {
            return cardsRepository.findByCustomerId(id);
        }
        return null;
    }
}
