package com.example.complaintmanagement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private ComplaintCategory complaintCategory;
    private Long courseId;
    private String title;
    private String description;
    private Integer status;
    private Integer priority;
    private Date createdDate;
    private Date updatedDate;
    private Boolean badWordsFlagged;
    private String photoUrl;
    private String notificationEmail;
    private String notificationSMS;

    @OneToOne
    @JoinColumn(name = "response_id") // Foreign key column
    private Response response;

}
