package com.example.complaintmanagement.Repositories;

import com.example.complaintmanagement.Entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
