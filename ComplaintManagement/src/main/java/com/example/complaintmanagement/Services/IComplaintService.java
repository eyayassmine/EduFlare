package com.example.complaintmanagement.Services;

import com.example.complaintmanagement.Entities.Complaint;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IComplaintService {
    Complaint addComplaint(Complaint complaint);
    List<Complaint> retrieveAllComplaints();

    Complaint retrieveComplaint(Long id);
    Complaint updateComplaint(Complaint complaint, Long id);

    Complaint setPriorityComplaint(Complaint complaint, Long id);

    void deleteComplaint(Long id);
    String uploadPhoto(Long id, MultipartFile file);
    List<Complaint> SortComplaintPriority();

    List<Complaint> SortComplaintLUpdatedDate();
    List<Complaint> SortComplaintCreateDate();
    List<Complaint> searchComplaints(String searchText);
    List<Complaint> searchComplaintsStatus(String searchText);

    List<Complaint> searchComplaintsCategory(String searchText);

    List<Complaint> filterComplaints(String filterText);
}
