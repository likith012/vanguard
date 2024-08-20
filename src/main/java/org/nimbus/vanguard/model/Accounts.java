package org.nimbus.vanguard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "accounts")
@Getter @Setter
public class Accounts {

    private long customerId;

    @Id
    private long accountNumber;

    private String accountType;

    private String branchAddress;

    private Date createDt;

}
