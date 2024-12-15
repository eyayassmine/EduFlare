package com.example.complaintmanagement.Services;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Repositories.ComplaintRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ComplaintServiceImp implements IComplaintService{

    @Autowired
    ComplaintRepository complaintr;


    @Override
    public Complaint addComplaint(Complaint complaint) {
        complaint.setStatus(0);             // Set status to 0 by default
        complaint.setCreatedDate(new Date()); // Set createdDate to current date

        return complaintr.save(complaint);    }

    @Override
    public List<Complaint> retrieveAllComplaints() {
        return  (List<Complaint>) complaintr.findAll();
    }


    @Override
    public Complaint retrieveComplaint(Long id) {
        return complaintr.findById(id).orElse(null);    }

    @Override
    public Complaint updateComplaint(Complaint complaint, Long id) {
        if ((!complaintr.existsById(id)) && (complaint.getStatus() !=0) ) {
            // If the project doesn't exist, you can handle the situation according to your requirements.
            // For example, you can throw an exception or return null.
            // Here, we are just returning null for simplicity.
            return null;
        }
        Complaint existingComplaint = complaintr.findById(id).orElse(null);
        existingComplaint.setUpdatedDate(new Date()); // Set updatedDate to current date
        // Set the ID of the project to ensure it's updated properly
        existingComplaint.setId(id);

        // Save the updated project
        return complaintr.save(existingComplaint);    }

    @Override
    public Complaint setPriorityComplaint(Complaint complaint, Long id) {
        if ((!complaintr.existsById(id)) && (complaint.getStatus() !=0) ) {
            // If the project doesn't exist, you can handle the situation according to your requirements.
            // For example, you can throw an exception or return null.
            // Here, we are just returning null for simplicity.
            return null;
        }
        Complaint existingComplaint = complaintr.findById(id).orElse(null);
        assert existingComplaint != null;
        existingComplaint.setPriority(complaint.getPriority());
        existingComplaint.setStatus(1);

        return complaintr.save(existingComplaint);    }

    @Override
    public void deleteComplaint(Long id) {
        complaintr.deleteById(id);
    }
}
