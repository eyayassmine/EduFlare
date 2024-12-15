package com.example.complaintmanagement.Services;

import com.example.complaintmanagement.Entities.Complaint;
import com.example.complaintmanagement.Entities.Response;
import com.example.complaintmanagement.Repositories.ComplaintRepository;
import com.example.complaintmanagement.Repositories.ResponseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ResponseServiceImp implements IResponseService {

    @Autowired
 ComplaintRepository complaintr;
    @Autowired
    ResponseRepository responser;


    @Override
    public Response addResponse(Response response, Long complaintId) {

        Complaint complaint = complaintr.findById(complaintId)
                .orElseThrow(() -> new IllegalArgumentException("Complaint not found with ID: " + complaintId));

        response.setCreatedDate(new Date()); // Set createdDate to current date
        response.setComplaint(complaint);
        complaintr.save(complaint);

        return responser.save(response);
        // Add response to the complaint's response list
    }

    @Override
    public List<Response> retrieveAllResponses() {
        return  (List<Response>) responser.findAll();
    }

    @Override
    public Response retrieveResponse(Long id) {
        return responser.findById(id).orElse(null);
    }

    @Override
    public Response updateResponse(Response response, Long id) {
        if ((!responser.existsById(id)) && (response.getStatus() !=0) ) {
            // If the project doesn't exist, you can handle the situation according to your requirements.
            // For example, you can throw an exception or return null.
            // Here, we are just returning null for simplicity.
            return null;
        }
        Response existingResponse = responser.findById(id).orElse(null);
        //existingresponse.setTitle(badWordsFilterService.censorBadWords(response.getDescription()));
        //existingResponse.setDescription(badWordsFilterService.censorBadWords(response.getDescription()));
        existingResponse.setUpdatedDate(new Date()); // Set updatedDate to current date
        // Set the ID of the project to ensure it's updated properly
        existingResponse.setId(id);

        // Save the updated project
        return responser.save(existingResponse);    }

    @Override
    public void deleteResponse(Long id) {
        responser.deleteById(id);

    }
}
