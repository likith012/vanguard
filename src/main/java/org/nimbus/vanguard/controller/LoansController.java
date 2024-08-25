package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Customer;
import org.nimbus.vanguard.model.Loans;
import org.nimbus.vanguard.repository.CustomerRepository;
import org.nimbus.vanguard.repository.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    @GetMapping("/loans")
    public List<Loans> getLoans(@RequestParam String email) {
        Long id = customerRepository.findByEmail(email).map(Customer::getId).orElse(null);
        if (id != null) {
            return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        }
        return null;
    }
}
