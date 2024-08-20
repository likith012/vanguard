package org.nimbus.vanguard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "contact_messages")
@Getter @Setter
public class Contact {

    @Id
    private String contactId;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    private Date createDt;

}
