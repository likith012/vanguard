package org.nimbus.vanguard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "account_transactions")
@Getter @Setter
public class AccountTransactions {

    @Id
    private String transactionId;

    private long accountNumber;

    private long customerId;

    private Date transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private Date createDt;

}
