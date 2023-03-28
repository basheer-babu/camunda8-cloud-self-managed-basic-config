package com.camunda.service;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ProductService {

    @ZeebeWorker(type = "productorder", autoComplete = true)
    public void serviceTaskImplementation(ActivatedJob activatedJob) {
        Map<String, Object> variableMap = activatedJob.getVariablesAsMap();
        log.info("Service Task Received" + variableMap.get("name"));
    }

    //, autoComplete = true
    @ZeebeWorker(type = "logRequest")
    public void logRequest(ActivatedJob activatedJob, JobClient client) {
        Map<String, Object> variableMap = activatedJob.getVariablesAsMap();
        log.info("Request Logged: " + variableMap);
        variableMap.put("name", "Eidiko");
        client.newCompleteCommand(activatedJob.getKey())
                .variables(variableMap)
                .send();
    }

    @ZeebeWorker(type = "notifyEmployee", autoComplete = true)
    public void notifyEmployee(ActivatedJob activatedJob) {
        Map<String, Object> variableMap = activatedJob.getVariablesAsMap();
        log.info("Employee Notified: " + variableMap);
    }

}