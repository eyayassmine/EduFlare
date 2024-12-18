package com.example.complaintmanagement.Services;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Repositories.ComplaintRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.example.complaintmanagement.Constants.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ComplaintServiceImp implements IComplaintService{

    @Autowired
    ComplaintRepository complaintr;
    @Autowired
    private BadWordsFilterService badWordsFilterService;

    @Override
    public Complaint addComplaint(Complaint complaint) {
        complaint.setStatus(0);             // Set status to 0 by default
        complaint.setCreatedDate(new Date()); // Set createdDate to current date
        complaint.setTitle(badWordsFilterService.censorBadWords(complaint.getTitle()));
        complaint.setDescription(badWordsFilterService.censorBadWords(complaint.getDescription()));
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
        existingComplaint.setTitle(badWordsFilterService.censorBadWords(complaint.getDescription()));
        existingComplaint.setDescription(badWordsFilterService.censorBadWords(complaint.getDescription()));
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

    @Override
    public String uploadPhoto(Long id, MultipartFile file) {

        Complaint complaint = complaintr.findById(id).orElse(null);
        String photoUrl = photofunction.apply(id, file);
        assert complaint != null;
        complaint.setPhotoUrl(photoUrl);
        complaintr.save(complaint);
        return photoUrl;
    }


    ///
    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1 )).orElse("png");

    private final BiFunction<Long, MultipartFile, String> photofunction = (id, image) ->
    {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try{
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/complaints/image/" + filename).toUriString();
        }catch (Exception exception){
            throw new RuntimeException("Unable to save image");
        }
    };

    @Override
    public List<Complaint> SortComplaintPriority() {
        return complaintr.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<Complaint> SortComplaintLUpdatedDate() {
        return null;
    }

    @Override
    public List<Complaint> SortComplaintCreateDate() {
        return complaintr.findAllByCreatedDateAsc();
    }

    @Override
    public List<Complaint> searchComplaints(String searchText) {
        return null;
    }

    @Override
    public List<Complaint> searchComplaintsStatus(String searchText) {
        return complaintr.findComplaintsByStatus(searchText);
    }

    @Override
    public List<Complaint> searchComplaintsCategory(String searchText) {
        return complaintr.findComplaintsByComplaintCategory(searchText);
    }

    @Override
    public List<Complaint> filterComplaints(String filterText) {
        return complaintr.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filterText, filterText);

    }

}
