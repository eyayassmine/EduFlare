package com.example.complaintmanagement.Controllers;

import com.example.complaintmanagement.Entities.Response;
import com.example.complaintmanagement.Services.IResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Response")
@CrossOrigin(origins = "http://localhost:4200")
public class ResponseController {

    IResponseService responseService;


    @PostMapping("/addResponse")
    @ResponseBody
    public Response addResponse(@RequestBody Response response , @PathVariable("complaintId") Long id)

    {
        return responseService.addResponse(response, id);

    }
    @GetMapping("/retrieve-response/{responseId}")

    @ResponseBody

    public Response retrieveResponse(@PathVariable(value = "responseId") Long id) {

        return responseService.retrieveResponse(id);

    }

    @GetMapping("/retrieve-all-responses")
    @ResponseBody
    public List<Response> getResponses() {

        return responseService.retrieveAllResponses();
    }


    @PutMapping("/modify-response/{responseId}")

    @ResponseBody

    public Response modifyResponse(@RequestBody Response response, @PathVariable("responseId") Long id) {

        return responseService.updateResponse(response, id);

    }


    @DeleteMapping("/remove-response/{responseId}")

    @ResponseBody

    public void removeResponse(@PathVariable("responseId") Long id) {

        responseService.deleteResponse(id);

    }

}
