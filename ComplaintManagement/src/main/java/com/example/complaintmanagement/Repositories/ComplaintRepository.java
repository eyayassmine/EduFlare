package com.example.complaintmanagement.Repositories;

import com.example.complaintmanagement.Entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
