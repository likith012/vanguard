package org.nimbus.vanguard.controller;

import lombok.RequiredArgsConstructor;
import org.nimbus.vanguard.model.Loans;
import org.nimbus.vanguard.repository.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/loans")
    public List<Loans> getLoans(@RequestParam long id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
