package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.AccountTransactions;
import org.nimbus.vanguard.model.Customer;
import org.nimbus.vanguard.repository.AccountTransactionsRepository;
import org.nimbus.vanguard.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/balance")
    public List<AccountTransactions> getBalance(@RequestParam String email) {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
        }
        return null;
    }
}
