package com.example.complaintmanagement.Repositories;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Entities.ComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Complaint c SET c.priority = :priority, c.status = 1 WHERE c.id = :complaintId")
    void setPriority(Long complaintId, Integer priority);


    // Method for user update (only allowed if status is 0)
    @Transactional
    @Modifying
    @Query("UPDATE Complaint c SET c.title = :title, c.complaintCategory = :complaintCategory, " +
            "c.description = :description, c.courseId = :courseId, c.updatedDate = CURRENT_TIMESTAMP " +
            "WHERE c.id = :complaintId AND c.status = 0")
    void specialUpdate(Long complaintId, String title, ComplaintCategory complaintCategory,
                       String description, Long courseId);



}
