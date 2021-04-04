package com.meryemefe.kartacatask.controller;

import com.meryemefe.kartacatask.entity.Response;
import com.meryemefe.kartacatask.model.MethodType;
import com.meryemefe.kartacatask.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ChartController {

    private ResponseService responseService;

    @Autowired
    public ChartController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/chart")
    public String chart(Model model){

        List<Integer> respTimesGet = new LinkedList<>();
        List<Integer> respTimesPost = new LinkedList<>();
        List<Integer> respTimesPut = new LinkedList<>();
        List<Integer> respTimesDelete = new LinkedList<>();
        List<Timestamp> timestampList = new LinkedList<>();

        List<Response> responses = responseService.getResponsesInLastHour();

        for (Response data: responses) {
            if (data.getMethodType().equals(MethodType.GET.name())){
                respTimesGet.add(data.getResponseTime());
                respTimesPost.add(null);
                respTimesPut.add(null);
                respTimesDelete.add(null);
            } else if (data.getMethodType().equals(MethodType.POST.name())) {
                respTimesPost.add(data.getResponseTime());
                respTimesGet.add(null);
                respTimesPut.add(null);
                respTimesDelete.add(null);
            } else if (data.getMethodType().equals(MethodType.PUT.name())) {
                respTimesPut.add(data.getResponseTime());
                respTimesGet.add(null);
                respTimesPost.add(null);
                respTimesDelete.add(null);
            } else if (data.getMethodType().equals(MethodType.DELETE.name())) {
                respTimesDelete.add(data.getResponseTime());
                respTimesGet.add(null);
                respTimesPost.add(null);
                respTimesPut.add(null);
            }
            timestampList.add(data.getTimestamp());
        }

        model.addAttribute("valRespTimeGet", respTimesGet);
        model.addAttribute("valRespTimePost", respTimesPost);
        model.addAttribute("valRespTimePut", respTimesPut);
        model.addAttribute("valRespTimeDelete", respTimesDelete);
        model.addAttribute("valTimestamp", timestampList);

        return "chart";
    }

}
