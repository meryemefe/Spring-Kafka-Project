package com.meryemefe.kartacatask.service;

import com.meryemefe.kartacatask.entity.Response;
import com.meryemefe.kartacatask.model.MethodType;
import com.meryemefe.kartacatask.model.ResponseDTO;
import com.meryemefe.kartacatask.repo.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class ResponseService {

    private final static int MAX_RESPONSE_TIME = 3000;

    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public void createResponse(MethodType type){
        int responseTime = (int)(Math.random() * (MAX_RESPONSE_TIME + 1));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMethodType(type);
        responseDTO.setResponseTime(responseTime);
        responseDTO.setTimestamp(timestamp);

        String log = responseDTO.toStringForFile();
        try {
            writeLogToFile(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLogToFile( String log) throws IOException {
        File file = new File("./response_logs.log");
        if (!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        fw.write(log);
        fw.close();
    }

    public Response saveResponseToDB(ResponseDTO responseDTO){
        Response response = new Response();
        response.setMethodType(responseDTO.getMethodType().name());
        response.setResponseTime(responseDTO.getResponseTime());
        response.setTimestamp(responseDTO.getTimestamp());

        return responseRepository.save(response);
    }

    public List<Response> getResponsesInLastHour(){
        Timestamp timestampOfLastHour = Timestamp.from(Instant.now().minus(Duration.ofHours(1)));
        return responseRepository.findAllByTimestampAfter(timestampOfLastHour);
    }

}

