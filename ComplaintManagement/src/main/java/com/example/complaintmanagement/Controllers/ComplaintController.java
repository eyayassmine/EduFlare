package com.example.complaintmanagement.Controllers;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Services.ComplaintServiceImp;
import com.example.complaintmanagement.Services.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.example.complaintmanagement.Constants.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/Complaint")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

    IComplaintService complaintService;
    ComplaintServiceImp complaintServiceImp;

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

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file)
    {
        return ResponseEntity.ok().body(complaintServiceImp.uploadPhoto(id, file));

    }

    @GetMapping(path = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException
    {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }


    @GetMapping("/retrieve-sort-priorityAsc")
    @ResponseBody
    public List<Complaint> SortComplaintsByPriority() {

        return complaintService.SortComplaintPriority();
    }

    @GetMapping("/retrieve-sort-createdateAsc")
    @ResponseBody
    public List<Complaint> SortComplaintsByCreatedDate() {
        return complaintService.SortComplaintCreateDate();
    }

    @GetMapping("/search-Status")
    public ResponseEntity<List<Complaint>> filterComplaintsStatus(@RequestParam String searchText) {
        List<Complaint> filteredComplaints = complaintService.searchComplaintsStatus(searchText);
        return new ResponseEntity<>(filteredComplaints, HttpStatus.OK);
    }

    @GetMapping("/search-category")
    public ResponseEntity<List<Complaint>> filterComplaintsCategory(@RequestParam String searchText) {
        List<Complaint> filteredComplaints = complaintService.searchComplaintsCategory(searchText);
        return new ResponseEntity<>(filteredComplaints, HttpStatus.OK);
    }


    @GetMapping("/filter-title-description")
    public ResponseEntity<List<Complaint>> filterComplaintsTitleDescription(@RequestParam String searchText) {
        List<Complaint> filteredComplaints = complaintService.filterComplaints(searchText);
        return new ResponseEntity<>(filteredComplaints, HttpStatus.OK);
    }



}
