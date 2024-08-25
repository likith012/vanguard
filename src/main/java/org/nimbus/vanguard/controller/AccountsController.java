package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Accounts;
import org.nimbus.vanguard.model.Customer;
import org.nimbus.vanguard.repository.AccountsRepository;
import org.nimbus.vanguard.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/account")
    public Accounts getAccount(@RequestParam String email) {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            return accountsRepository.findByCustomerId(customer.getId());
        }
        return null;
    }
}
