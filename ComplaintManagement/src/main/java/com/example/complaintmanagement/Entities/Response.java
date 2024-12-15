package com.example.complaintmanagement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long complaintId;
    private String responseText;
    private Date createdDate;
    private Date updatedDate;
    private Long respondentId;
    private Integer status;
    private URL attachment;
    private Boolean notificationSent;

    @OneToOne(mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
    private Complaint complaint;



}
