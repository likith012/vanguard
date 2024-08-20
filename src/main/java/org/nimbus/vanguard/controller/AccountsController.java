package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Accounts;
import org.nimbus.vanguard.repository.AccountsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/account")
    public Accounts getAccount(@RequestParam long id) {
        return accountsRepository.findByCustomerId(id);
    }
}
