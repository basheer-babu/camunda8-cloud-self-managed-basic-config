package com.camunda.controller;

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private ZeebeClientLifecycle client;

    @PostMapping("/startprocess")
    public void startProcess() {

        Map<String,Object> m = new HashMap();
        m.put("test1","test1");
        m.put("test2","test2");
        m.put("test3","test3");


        client
                .newCreateInstanceCommand()
                .bpmnProcessId("ID_Demo1")
                .latestVersion()
//                .variables(m)
                .send()
                .join();
        log.info("Process Started");
    }


}