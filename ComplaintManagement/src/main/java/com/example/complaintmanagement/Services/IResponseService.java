package com.example.complaintmanagement.Services;

import com.example.complaintmanagement.Entities.Response;

import java.util.List;

public interface IResponseService {

    Response addResponse(Response response, Long complaintId);
    List<Response> retrieveAllResponses();

    Response retrieveResponse(Long id);
    Response updateResponse(Response response, Long id);

    void deleteResponse(Long id);

}
