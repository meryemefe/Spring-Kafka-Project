package com.meryemefe.kartacatask.controller;

import com.meryemefe.kartacatask.model.MethodType;
import com.meryemefe.kartacatask.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private ResponseService responseService;

    @Autowired
    public RequestController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping
    public void get(){
        responseService.createResponse(MethodType.GET);
    }

    @PostMapping
    public void post(){
        responseService.createResponse(MethodType.POST);
    }

    @PutMapping
    public void put(){
        responseService.createResponse(MethodType.PUT);
    }

    @DeleteMapping
    public void delete(){
        responseService.createResponse(MethodType.DELETE);
    }


}

