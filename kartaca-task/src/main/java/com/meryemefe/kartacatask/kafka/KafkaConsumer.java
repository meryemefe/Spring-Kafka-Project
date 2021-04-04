package com.meryemefe.kartacatask.kafka;

import com.meryemefe.kartacatask.model.MethodType;
import com.meryemefe.kartacatask.model.ResponseDTO;
import com.meryemefe.kartacatask.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class KafkaConsumer {

    private ResponseService responseService;

    @Autowired
    public KafkaConsumer(ResponseService responseService) {
        this.responseService = responseService;
    }

    @KafkaListener(topics = "topic_kartaca", groupId = "group_id")
    public void getMessage(String message){
        System.out.println(message);
        ResponseDTO responseDTO = new ResponseDTO();
        String[] s = message.split(" ");
        responseDTO.setMethodType(MethodType.valueOf(s[0]));
        responseDTO.setResponseTime(Integer.parseInt(s[1]));
        responseDTO.setTimestamp(new Timestamp( Long.parseLong(s[2])) );

        responseService.saveResponseToDB(responseDTO);
    }
}
