package org.nimbus.vanguard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "notice_details")
@Getter @Setter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private Date noticeBegDt;

    private Date noticeEndDt;

    @JsonIgnore
    private Date createDt;

    @JsonIgnore
    private Date updateDt;

}
