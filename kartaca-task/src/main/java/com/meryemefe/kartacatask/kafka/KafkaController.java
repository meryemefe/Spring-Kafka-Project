package com.meryemefe.kartacatask.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@EnableScheduling
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @Scheduled(fixedDelay = 2000)
    public void readFile(){
        File file = new File("./response_logs.log");
        String filename = "./response_logs.log";

        try {
            if (!file.exists())
                file.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;
            while( (line = br.readLine()) != null) {
                producer.sendMessage(line);
            }
            br.close();
            PrintWriter pw = new PrintWriter(filename);

            pw.write("");
            pw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
