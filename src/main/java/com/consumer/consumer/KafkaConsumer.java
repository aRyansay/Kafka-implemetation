package com.consumer.consumer;

import com.consumer.dto.EmployeeDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {

    public static final String EMPLOYEE_TOPIC = "employee_topic";
    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = EMPLOYEE_TOPIC)
    public void consumer (String message){
      EmployeeDto employeeDto;
        try{
            employeeDto = objectMapper.readValue(  , EmployeeDto.class);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("========================");
        System.out.println(employeeDto.getId());
        System.out.println(employeeDto.getName());
        System.out.println(employeeDto.getEmail());
        System.out.println(employeeDto.getCompany());
        System.out.println(employeeDto.getAddressDto().getCity());
        System.out.println("========================");
    }

}
