package com.example.complaintmanagement.Controllers;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Services.ComplaintServiceImp;
import com.example.complaintmanagement.Services.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Complaint")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

    IComplaintService complaintService;
    @PostMapping("/addComplaint")
    @ResponseBody
    public Complaint addComplaint(@RequestBody Complaint complaint)

    {
        return complaintService.addComplaint(complaint);

    }
    @GetMapping("/retrieve-complaint/{complaintId}")

    @ResponseBody

    public Complaint retrieveComplaint(@PathVariable(value = "complaintId") Long id) {

        return complaintService.retrieveComplaint(id);

    }

    @GetMapping("/retrieve-all-complaints")
    @ResponseBody
    public List<Complaint> getComplaints() {

        return complaintService.retrieveAllComplaints();
    }


    @PutMapping("/modify-complaint/{complaintId}")

    @ResponseBody

    public Complaint modifyComplaint(@RequestBody Complaint complaint, @PathVariable("complaintId") Long id) {

        return complaintService.updateComplaint(complaint, id);

    }

    @PutMapping("/set-priority-complaint/{complaintId}")

    @ResponseBody

    public Complaint setPriorityComplaint(@RequestBody Complaint complaint, @PathVariable("complaintId") Long id) {

        return complaintService.setPriorityComplaint(complaint, id);

    }


    @DeleteMapping("/remove-complaint/{complaintId}")

    @ResponseBody

    public void removeComplaint(@PathVariable("complaintId") Long id) {

        complaintService.deleteComplaint(id);

    }


}
