package com.aababou.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

/*
author otman
    */
@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

//    @KafkaListener(topics = "patient",groupId = "analytics-service")
//    public void consumeEvent(byte[] event){
//        try {
//            PatientEvent patientEvent = PatientEvent.parseFrom(event);
//            //... perform any business related to analytics-service  here
//            log.info("Patient event received: " + patientEvent.toString());
//        } catch (InvalidProtocolBufferException e) {
//            log.error("Error Deserializing eveent :{}",e.getMessage());
//        }
//
//    }


    @KafkaListener(topics = "patient", groupId = "test-group")
    public void consumeEvent(byte[] event){
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info("✅ Received: " + patientEvent);
        } catch (Exception e) {
            log.error("❌ Error: {}", e.getMessage());
        }
    }
}
